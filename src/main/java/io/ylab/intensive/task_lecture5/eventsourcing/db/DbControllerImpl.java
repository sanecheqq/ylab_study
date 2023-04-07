package io.ylab.intensive.task_lecture5.eventsourcing.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

@Component("dbController")
public class DbControllerImpl implements DbController {
    private final DataSource dataSource;

    @Autowired
    public DbControllerImpl (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertPerson(String[] values) throws SQLException {
        checkValuesCount(values);
        System.out.println("[db] Inserting person " + Arrays.toString(values) + "...");
        String insertQuery = "INSERT INTO person (person_id, first_name, last_name, middle_name) values (?,?,?,?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setLong(1, Long.parseLong(values[0]));
            statement.setString(2, values[1]);
            statement.setString(3, values[2]);
            statement.setString(4, values[3]);
            statement.executeUpdate();
        }
    }

    public void updatePerson(String[] values) throws SQLException {
        checkValuesCount(values);
        System.out.println("[db] Updating person " + Arrays.toString(values) + "...");
        String updateQuery = "UPDATE person\n" +
                "\tSET first_name = ?, last_name = ?, middle_name = ?\n" +
                "\tWHERE person_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, values[1]);
            statement.setString(2, values[2]);
            statement.setString(3, values[3]);
            statement.setLong(4, Long.parseLong(values[0]));
            statement.executeUpdate();
        }
    }

    public void deletePerson(Long personId) throws SQLException {
        System.out.println("[db] Deleting person with id " + personId + "...");
        String deleteQuery = "DELETE FROM person\n" +
                "\tWHERE person_id = ?;";
        try (Connection connection = dataSource.getConnection();
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
