package io.ylab.intensive.task_lecture5.eventsourcing.db;

import java.sql.SQLException;

public interface DbController {
    void insertPerson(String[] values) throws SQLException;

    void updatePerson(String[] values) throws SQLException;

    void deletePerson(Long personId) throws SQLException;
}
