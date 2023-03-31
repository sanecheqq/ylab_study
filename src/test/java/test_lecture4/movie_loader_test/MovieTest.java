package test_lecture4.movie_loader_test;

import io.ylab.intensive.task_lecture4.DbUtil;
import io.ylab.intensive.task_lecture4.movie.MovieLoader;
import io.ylab.intensive.task_lecture4.movie.MovieLoaderImpl;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

public class MovieTest {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = initDb();
        MovieLoader movieLoader = new MovieLoaderImpl(dataSource);

        File dataFile = new File("film.csv");
        movieLoader.loadData(dataFile);

        /*
         * SELECT subject, COUNT(id) as count
         * 	FROM movie
         * 	GROUP BY subject;
         */
    }

    private static DataSource initDb() throws SQLException {
        String createMovieTable = "drop table if exists movie;"
                + "CREATE TABLE IF NOT EXISTS movie (\n"
                + "\tid bigserial NOT NULL,\n"
                + "\t\"year\" int4,\n"
                + "\tlength int4,\n"
                + "\ttitle varchar,\n"
                + "\tsubject varchar,\n"
                + "\tactors varchar,\n"
                + "\tactress varchar,\n"
                + "\tdirector varchar,\n"
                + "\tpopularity int4,\n"
                + "\tawards bool,\n"
                + "\tCONSTRAINT movie_pkey PRIMARY KEY (id)\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createMovieTable, dataSource);
        return dataSource;
    }
}
