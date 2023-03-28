package io.ylab.intensive.lesson04.eventsourcing.api;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson04.RabbitMQUtil;
import io.ylab.intensive.lesson04.eventsourcing.Person;
import java.util.List;

public class ApiApp {
    public static void main(String[] args) throws Exception {
        String QUEUE_NAME = "queue1";
        ConnectionFactory connectionFactory = initMQ();
        try (Connection connection = connectionFactory.newConnection();
             PersonApi api = new PersonApiImpl(connection.createChannel(), QUEUE_NAME)) {
            api.savePerson(1L, "Alex", "Pipkin", "Alexovich");
            api.savePerson(2L, "Ivan", "Popkin", "Ivanich");
            wait100millis();
            printAllPersons(api.findAll());
            api.deletePerson(2L);
            api.savePerson(1L, "AlexUpd", "PipkinUpd", "AlexovichUpd");
            wait100millis();
            System.out.println("Result of finding person with person_id = 1:" + api.findPerson(1L));
            System.out.println("Result of finding person with person_id = 2:" + api.findPerson(2L));
            wait100millis();
            api.deletePerson(100L);
        }
    }

    private static ConnectionFactory initMQ() throws Exception {
        return RabbitMQUtil.buildConnectionFactory();
    }

    private static void printAllPersons(List<Person> data) {
        for (Person person : data) {
            System.out.println(person);
        }
    }

    private static void wait100millis() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
