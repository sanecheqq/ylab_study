package io.ylab.intensive.lesson05.eventsourcing.api;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Connection;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Component
public class MessageSender {
    private final Connection connection;
    private final Channel channel;
    private static final String queueName = "default";

    @Autowired
    public MessageSender(ConnectionFactory connectionFactory) throws IOException, TimeoutException {
        this.connection = connectionFactory.newConnection();
        this.channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
    }

    @PreDestroy
    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    public void sendMessage(String message) throws IOException {
        channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("[ms] Sent \"" + message + "\"");
    }
}
