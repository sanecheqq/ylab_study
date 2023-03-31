package io.ylab.intensive.lesson04.filesort;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileSortImpl implements FileSorter {
    private final int BATCH_SIZE = 50;
    private final DataSource dataSource;

    public FileSortImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public File sort(File data) {
        try {
            readFileToDB(data);
            writeSortDBToFile(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    private void readFileToDB(File data) throws SQLException, IOException {
        String insertQuery = "INSERT INTO numbers (val) values (?);";
        try (BufferedReader reader = new BufferedReader(new FileReader(data));
             Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            connection.setAutoCommit(false);
            readFileToBatches(reader, statement, connection);
        }
    }

    private void readFileToBatches(BufferedReader reader, PreparedStatement statement,
                                   Connection connection) throws SQLException, IOException {
        String line;
        int linesCount = 0;
        while ((line = reader.readLine()) != null) {
            long num = Long.parseLong(line);
            linesCount++;
            statement.setLong(1, num);
            statement.addBatch();
            if (linesCount % BATCH_SIZE == 0) {
                executeBatchAndCommit(statement, connection);
            }
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

    private void writeSortDBToFile(File data) throws SQLException, IOException {
        String sortQuery = "SELECT val FROM numbers\n" +
                "\tORDER BY val DESC;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sortQuery)) {
            ResultSet sortedDB = statement.executeQuery();
            writeToFile(data, sortedDB);
        }
    }

    private void writeToFile(File data, ResultSet sortedDB) throws SQLException, IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(data))) {
            while (sortedDB.next()) {
                long numToWrite = sortedDB.getLong(1);
                writer.write(numToWrite + "\n");
            }
        }
    }
}
