package io.ylab.intensive.lesson05.eventsourcing.api;


import io.ylab.intensive.lesson05.eventsourcing.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component("personApi")
public class PersonApiImpl implements PersonApi {
    private final MessageSender messageSender;
    private final DataSource dataSource;

    @Autowired
    public PersonApiImpl(MessageSender messageSender, DataSource dataSource) {
        this.messageSender = messageSender;
        this.dataSource = dataSource;
    }

    @Override
    public void deletePerson(Long personId) {
        if (findPerson(personId) == null) {
            System.out.println("[Warning] Delete: trying to delete person with person_id=" + personId + " but it doesn't exist");
            return;
        }
        try {
            String message = String.format("delete;%d", personId);
            messageSender.sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void savePerson(Long personId, String firstName, String lastName, String middleName) {
        String query = findPerson(personId) == null ? "insert" : "update";
        try {
            String message = String.format("%s;%d;%s;%s;%s", query, personId, firstName, lastName, middleName);
            messageSender.sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
