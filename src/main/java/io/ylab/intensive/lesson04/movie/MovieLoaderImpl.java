package io.ylab.intensive.lesson04.movie;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.TreeMap;
import javax.sql.DataSource;

public class MovieLoaderImpl implements MovieLoader {
    private DataSource dataSource;
    public MovieLoaderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadData(File file) {
        Map<Integer, Movie> movies = new TreeMap<>();
        readFileToMap(file, movies);
        String insertQuery = "INSERT INTO movie (year, length, title, subject, actors, actress, director, popularity, awards)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            fillDBFromMap(statement, movies);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void readFileToMap(File file, Map<Integer, Movie> movies) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            skipTwoLines(reader);
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Movie movie = new Movie();
                movie.setYear(parseInt(data[0]));
                movie.setLength(parseInt(data[1]));
                movie.setTitle(data[2]);
                movie.setSubject(data[3]);
                movie.setActors(data[4]);
                movie.setActress(data[5]);
                movie.setDirector(data[6]);
                movie.setPopularity(parseInt(data[7]));
                movie.setAwards(parseBoolean(data[8]));
                movies.put(id++, movie);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void skipTwoLines(BufferedReader reader) throws IOException {
        reader.readLine();
        reader.readLine();
    }

    private Integer parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }
    private Boolean parseBoolean(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
            return null;
        }
    }

    private void fillDBFromMap(PreparedStatement statement, Map<Integer, Movie> movies) throws SQLException {
        for (Map.Entry<Integer, Movie> entry : movies.entrySet()) {
            fillTheStatement(statement, entry.getValue());
            statement.executeUpdate();
        }
    }

    private void fillTheStatement(PreparedStatement statement, Movie movie) throws SQLException {
        setIntOrNull(statement, movie.getYear(), 1);
        setIntOrNull(statement, movie.getLength(), 2);
        setStringOrNull(statement, movie.getTitle(), 3);
        setStringOrNull(statement, movie.getSubject(), 4);
        setStringOrNull(statement, movie.getActors(), 5);
        setStringOrNull(statement, movie.getActress(), 6);
        setStringOrNull(statement, movie.getDirector(), 7);
        setIntOrNull(statement, movie.getPopularity(), 8);
        setBooleanOrNull(statement, movie.getAwards(), 9);
    }

    private void setStringOrNull(PreparedStatement statement, String value, int index) throws SQLException {
        if (!value.equals("")) {
            statement.setString(index, value);
        } else {
            statement.setNull(index, Types.VARCHAR);
        }
    }

    private void setIntOrNull(PreparedStatement statement, Integer value, int index) throws SQLException {
        try {
            statement.setInt(index, value);
        } catch (Exception e){
            statement.setNull(index, Types.INTEGER);
        }
    }

    private void setBooleanOrNull(PreparedStatement statement, Boolean value, int index) throws SQLException {
        try {
            statement.setBoolean(index, value);
        } catch (Exception e) {
            statement.setNull(index, Types.BOOLEAN);
        }
    }
}
