package io.ylab.intensive.lesson05.messagefilter;

import java.io.File;
import java.sql.SQLException;

public interface DbController {
    void initDb (String tableName) throws SQLException;

    void fillDbFromFile(File file) throws Exception;

    void clearTable() throws SQLException;

    boolean findWord(String word) throws SQLException;
}
