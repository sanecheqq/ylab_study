package io.ylab.intensive.task_lecture5.sqlquerybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLQueryBuilderImpl implements SQLQueryBuilder {
    private final DataSource dataSource;

    @Autowired
    public SQLQueryBuilderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(connection.getCatalog(), connection.getSchema(), "%", new String[]{"TABLE"});
            while (rs.next()) {
                tables.add(rs.getString(3));
            }
        }
        return tables;
    }

    @Override
    public String queryForTable(String tableName) throws SQLException {
        if (!tableExists(tableName)) {
            return null;
        }
        StringBuilder selectQuery = new StringBuilder("SELECT ");
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getColumns(connection.getCatalog(), connection.getSchema(), tableName, "%");
            appendColumnsToQuery(selectQuery, rs);
        }
        selectQuery.append(" FROM ").append(tableName);
        return selectQuery.toString();
    }

    private static void appendColumnsToQuery(StringBuilder selectQuery, ResultSet rs) throws SQLException {
        while (rs.next()) {
            selectQuery.append(rs.getString(4));
            if (!rs.isLast()) {
                selectQuery.append(", ");
            }
        }
    }

    private boolean tableExists(String tableName) throws SQLException {
        return getTables().contains(tableName);
    }
}
