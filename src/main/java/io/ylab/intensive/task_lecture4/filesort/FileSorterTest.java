package io.ylab.intensive.task_lecture4.filesort;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;

import io.ylab.intensive.task_lecture4.DbUtil;
import io.ylab.intensive.task_lecture3.file_sort.Generator;

public class FileSorterTest {
    public static void main(String[] args) throws SQLException, IOException {
        File data = new Generator().generate("src/main/java/io/ylab/intensive/lesson04/filesort/data.txt", 1_000_000);
        DataSource dataSource = initDb();
        FileSorter fileSorter = new FileSortImpl(dataSource);

        long s = System.currentTimeMillis();
        File res = fileSorter.sort(data);
        long e = System.currentTimeMillis();
        System.out.println("Working time with batches: " + (e - s)); // ~37_000 millis for 1_000_000 long numbers
    }


    public static DataSource initDb() throws SQLException {
        String createSortTable = ""
                + "drop table if exists numbers;"
                + "CREATE TABLE if not exists numbers (\n"
                + "\tval bigint\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createSortTable, dataSource);
        return dataSource;
    }
}
