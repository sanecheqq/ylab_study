package io.ylab.intensive.task_lecture5.messagefilter;

import io.ylab.intensive.task_lecture5.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

@Component("dbController")
public class DbControllerImpl implements DbController {
    private final DataSource dataSource;

    @Autowired
    public DbControllerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void initDb(String tableName) throws SQLException {
        if (tableExists(tableName)) {
            clearTable();
            return;
        }
        String ddl = "CREATE TABLE "+ tableName +" (\n" +
                "\tid bigint primary key,\n" +
                "\tword varchar\n" +
                ");";
        DbUtil.applyDdl(ddl, dataSource);
    }

    private boolean tableExists(String tableName) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet tables = md.getTables(connection.getCatalog(), connection.getSchema(), "%", new String[]{"TABLE"});
            while (tables.next()) {
                if (tableName.equals(tables.getString(3))) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void fillDbFromFile(File file) throws Exception {
        final int BATCH_SIZE = 25;
        String insertQuery = "INSERT INTO obscene_words (id, word) values (?, ?);";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            BufferedReader reader = new BufferedReader(new FileReader(file))) {
            connection.setAutoCommit(false);
            String line;
            long readLines = 0;
            while ((line = reader.readLine()) != null) {
                readLines++;
                statement.setLong(1, readLines);
                statement.setString(2, line.toLowerCase());
                statement.addBatch();
                if (readLines % BATCH_SIZE == 0) {
                    executeBatchAndCommit(statement, connection);
                }
            }
            executeBatchAndCommit(statement, connection);
        }
    }

    private void executeBatchAndCommit(PreparedStatement statement, Connection connection) throws SQLException {
        try {
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
    }

    @Override
    public void clearTable() throws SQLException {
        String deleteQuery = "DELETE FROM obscene_words;";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.executeUpdate();
        }
    }

    @Override
    public boolean findWord(String word) throws SQLException {
        String selectQuery = "SELECT word FROM obscene_words\n" +
                "\tWHERE word = ?;";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, word);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }
}
