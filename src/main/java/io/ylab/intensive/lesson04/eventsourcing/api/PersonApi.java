package io.ylab.intensive.lesson04.eventsourcing.api;

import java.util.List;

import io.ylab.intensive.lesson04.eventsourcing.Person;

public interface PersonApi extends AutoCloseable {
    void deletePerson(Long personId);

    void savePerson(Long personId, String firstName, String lastName, String middleName);

    Person findPerson(Long personId);

    List<Person> findAll();

    @Override
    void close() throws Exception;
}
