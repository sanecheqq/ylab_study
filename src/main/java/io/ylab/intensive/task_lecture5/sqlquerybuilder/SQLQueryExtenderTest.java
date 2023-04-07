package io.ylab.intensive.task_lecture5.sqlquerybuilder;

import java.sql.SQLException;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SQLQueryExtenderTest {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        SQLQueryBuilder queryBuilder = applicationContext.getBean(SQLQueryBuilder.class);
        List<String> tables = queryBuilder.getTables();
        for (String tableName : tables) {
            System.out.println(queryBuilder.queryForTable(tableName));
        }
    }
}
