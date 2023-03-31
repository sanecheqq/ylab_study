package io.ylab.intensive.task_lecture4.eventsourcing.db;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import javax.sql.DataSource;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import io.ylab.intensive.task_lecture4.DbUtil;
import io.ylab.intensive.task_lecture4.RabbitMQUtil;

public class DbApp {
    static DataSource dataSource;
    public static void main(String[] args) throws Exception {
        String QUEUE_NAME = "queue1";
        dataSource = initDb();
        ConnectionFactory connectionFactory = initMQ();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("[*] Waiting for message. To exit press CTRL+F2 or button 'stop'");
        
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("[x] Received {" + message + "}");
            try {
                processMessage(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }finally {
                System.out.println("[x] Query completed.");
            }
        };
        channel.basicConsume(QUEUE_NAME, true,deliverCallback, consumerTag ->{});
    }

    private static ConnectionFactory initMQ() throws Exception {
        return RabbitMQUtil.buildConnectionFactory();
    }

    private static DataSource initDb() throws SQLException {
        String ddl = "drop table if exists person;"
                + "CREATE TABLE IF NOT EXISTS person (\n"
                + "person_id bigint primary key,\n"
                + "first_name varchar,\n"
                + "last_name varchar,\n"
                + "middle_name varchar\n"
                + ")";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(ddl, dataSource);
        return dataSource;
    }

    private static void processMessage(String msg) throws SQLException {
        String[] values = msg.split(";");
        String query = values[0];
        switch (query) {
            case "insert": {
                insertPerson(Arrays.copyOfRange(values, 1, values.length));
                break;
            }
            case "update": {
                updatePerson(Arrays.copyOfRange(values, 1, values.length));
                break;
            }
            case "delete": {
                deletePerson(Long.parseLong(values[1]));
                break;
            }
        }
    }

    private static void insertPerson(String[] values) throws SQLException {
        checkValuesCount(values);
        String insertQuery = "INSERT INTO person (person_id, first_name, last_name, middle_name) values (?,?,?,?);";
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setLong(1, Long.parseLong(values[0]));
            statement.setString(2, values[1]);
            statement.setString(3, values[2]);
            statement.setString(4, values[3]);
            statement.executeUpdate();
        }
    }

    private static void updatePerson(String[] values) throws SQLException {
        checkValuesCount(values);
        String updateQuery = "UPDATE person\n" +
                "\tSET first_name = ?, last_name = ?, middle_name = ?\n" +
                "\tWHERE person_id = ?;";
        try (java.sql.Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, values[1]);
            statement.setString(2, values[2]);
            statement.setString(3, values[3]);
            statement.setLong(4, Long.parseLong(values[0]));
            statement.executeUpdate();
        }
    }

    private static void deletePerson(Long personId) throws SQLException {
        String deleteQuery = "DELETE FROM person\n" +
                "\tWHERE person_id = ?;";
        try (java.sql.Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setLong(1, personId);
            statement.executeUpdate();
        }
    }

    private static void checkValuesCount(String[] values) throws SQLException {
        if (values.length != 4) {
            throw new SQLException("Wrong values count.");
        }
    }
}
