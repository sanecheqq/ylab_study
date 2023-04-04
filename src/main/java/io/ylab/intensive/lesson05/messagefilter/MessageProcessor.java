package io.ylab.intensive.lesson05.messagefilter;

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
import java.util.concurrent.TimeoutException;

@Component
public class MessageProcessor {
    private final Connection connection;
    private final Channel channel;
    private final String queueInput;
    private final String queueOutput;
    private DbController dbController;

    @Autowired
    public MessageProcessor (ConnectionFactory connectionFactory, String queueInput, String queueOutput) throws IOException, TimeoutException {
        this.connection = connectionFactory.newConnection();
        this.channel = connection.createChannel();
        this.queueInput = queueInput;
        this.queueOutput = queueOutput;
        channel.queueDeclare(queueInput, false, false, false, null);
        channel.queueDeclare(queueOutput, false, false, false, null);
    }

    @Autowired
    public void setDbController (DbController dbController) {
        this.dbController = dbController;
    }

    @PreDestroy
    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    public void waitForMessagesAndProcessIt() throws IOException {
        System.out.println("[*] MessageProcessor: waiting for message. To exit press CTRL+F2 or button 'stop'");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("[mp] Received \"" + message + "\"");
            try {
                String res = processMessage(message);
                sendMessage(res);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
        channel.basicConsume(queueInput, true,deliverCallback, consumerTag ->{});
    }

    private String processMessage(String message) throws SQLException {
        String[] words = message.trim().split("[^\\wа-яА-Я]+");
        String[] separators = message.split("[\\wа-яА-Я]+");
        StringBuilder result = new StringBuilder(separators[0]);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String wordInLowerCase = word.toLowerCase();
            if (dbController.findWord(wordInLowerCase.toLowerCase())) {
                char[] letters = word.toCharArray();
                int hiddenLettersCount = letters.length - 2;
                result
                    .append(letters[0])
                    .append("*".repeat(hiddenLettersCount))
                    .append(letters[letters.length-1]);
            } else {
                result.append(words[i]);
            }
            if (i+1 < separators.length) {
                result.append(separators[i+1]);
            }
        }
        return result.toString();
    }

    public void sendMessage(String message) throws IOException {
        channel.basicPublish("", queueOutput, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("[ms] Sent \"" + message + "\"");
    }

}
