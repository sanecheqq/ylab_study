package io.ylab.intensive.lesson04.persistentmap;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;


public class  PersistentMapImpl implements PersistentMap {

    private DataSource dataSource;
    private String mapName = "defaultName";

    public PersistentMapImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void init(String name) {
        mapName = name;
    }

    @Override
    public boolean containsKey(String key) throws SQLException {
//        return get(key) != null;
        String selectQuery = "SELECT value FROM persistent_map\n" +
                "\tWHERE map_name = ? AND KEY = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, mapName);
            statement.setString(2, key);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    @Override
    public List<String> getKeys() throws SQLException {
        List<String> keys = new LinkedList<>();
        String selectQuery = "SELECT KEY FROM persistent_map\n" +
                "\tWHERE map_name = ?;";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, mapName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                keys.add(rs.getString(1));
            }
            return keys;
        }

    }

    @Override
    public String get(String key) throws SQLException {
        String selectQuery = "SELECT value FROM persistent_map\n" +
                "\tWHERE map_name = ? AND KEY = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, mapName);
            statement.setString(2, key);
            ResultSet resultSet = statement.executeQuery();
            String value = null;
            if (resultSet.next()) {
                value = resultSet.getString(1);
            }
            return value;
        }
    }

    @Override
    public void remove(String key) throws SQLException {
        String deleteQuery = "DELETE FROM persistent_map\n" +
                "\tWHERE map_name = ? AND KEY = ?;";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setString(1, mapName);
            statement.setString(2, key);
            statement.executeUpdate();
        }
    }

    @Override
    public void put(String key, String value) throws SQLException {
        if (containsKey(key)) {
            remove(key);
        }
        String insertQuery = "INSERT INTO persistent_map (map_name, KEY, value) values (?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, mapName);
            statement.setString(2, key);
            statement.setString(3, value);
            statement.executeUpdate();
        }
    }

    @Override
    public void clear() throws SQLException {
        String deleteQuery = "DELETE FROM persistent_map WHERE map_name = ?;";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setString(1, mapName);
            statement.executeUpdate();
        }
    }
}
