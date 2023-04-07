package io.ylab.intensive.task_lecture5.sqlquerybuilder;

import java.sql.SQLException;
import java.util.List;

public interface SQLQueryBuilder {
    String queryForTable(String tableName) throws SQLException;

    List<String> getTables() throws SQLException;
}
