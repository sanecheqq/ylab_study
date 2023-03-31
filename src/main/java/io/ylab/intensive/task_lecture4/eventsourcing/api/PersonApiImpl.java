package io.ylab.intensive.task_lecture4.eventsourcing.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import io.ylab.intensive.task_lecture4.DbUtil;
import io.ylab.intensive.task_lecture4.eventsourcing.Person;

import javax.sql.DataSource;


public class PersonApiImpl implements PersonApi  {
    private final Channel channel;
    private final String QUEUE_NAME;
    private final DataSource dataSource;

    public PersonApiImpl(Channel channel, String QUEUE_NAME) throws IOException, SQLException {
        this.channel = channel;
        this.QUEUE_NAME = QUEUE_NAME;
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        dataSource = DbUtil.buildDataSource();
    }

    @Override
    public void close() throws IOException, TimeoutException {
        channel.close();
    }

    @Override
    public void deletePerson(Long personId) {
        if (findPerson(personId) == null) {
            System.out.println("Delete: trying to delete person with person_id=" + personId + " but it doesn't exist");
            return;
        }
        try {
            String message = String.format("delete;%d", personId);
            sendMessage(message);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void savePerson(Long personId, String firstName, String lastName, String middleName) {
        String query = queryToDo(personId);
        try {
            String message = String.format("%s;%d;%s;%s;%s;", query, personId, firstName, lastName, middleName);
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String queryToDo(Long personId) {
        return findPerson(personId) == null ? "insert" : "update";
    }


    private void sendMessage(String message) throws IOException {
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent {" + message + "}");
    }

    @Override
    public Person findPerson(Long personId) {
        String selectQuery = "SELECT * FROM person\n" +
                "\tWHERE person_id = ?;";
        Person person = null;
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, personId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return person;
    }

    @Override
    public List<Person> findAll() {
        String selectQuery = "SELECT * FROM person;";
        List<Person> personsData = new ArrayList<>();
        try (java.sql.Connection connection = dataSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(selectQuery)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                personsData.add(new Person(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return personsData;
    }
}
