package io.ylab.intensive.lesson05.eventsourcing.db;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

@Component
public class MessageReceiverAndProcessor {
    private final Connection connection;
    private final Channel channel;
    private DbController dbController;
    private static final String queueName = "default";

    @Autowired
    public MessageReceiverAndProcessor(ConnectionFactory connectionFactory) throws IOException, TimeoutException {
        this.connection = connectionFactory.newConnection();
        this.channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
    }


    @Autowired
    public void setDbController(DbControllerImpl dbController) {
        this.dbController = dbController;
    }

    @PreDestroy
    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    public void waitForMessagesAndProcessIt() throws IOException {
        System.out.println("[*] Waiting for message. To exit press CTRL+F2 or button 'stop'");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("[mp] Received \"" + message + "\"");
            try {
                if (processMessage(message)) {
                    System.out.println("[mp] Query completed successfully.");
                } else {
                    System.out.println("[mp] Unexpected query.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
        channel.basicConsume(queueName, true,deliverCallback, consumerTag ->{});
    }

    private boolean processMessage(String message) throws SQLException {
        String[] values = message.split(";");
        String query = values[0];
        switch (query) {
            case "insert": {
                dbController.insertPerson(Arrays.copyOfRange(values, 1, values.length));
                break;
            }
            case "update": {
                dbController.updatePerson(Arrays.copyOfRange(values, 1, values.length));
                break;
            }
            case "delete": {
                dbController.deletePerson(Long.parseLong(values[1]));
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
}
