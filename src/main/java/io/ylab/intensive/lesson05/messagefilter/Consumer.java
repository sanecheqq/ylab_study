package io.ylab.intensive.lesson05.messagefilter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.charset.StandardCharsets;

public class Consumer {
    public static void main (String[] args) throws Exception {
        String QUEUE_NAME = "output";
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        ConnectionFactory factory = applicationContext.getBean("connectionFactory", ConnectionFactory.class);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("[*] Consumer Major: waiting for message. To exit press CTRL+F2 or button 'stop'");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("[c] Received \"" + message + "\"");
        };
        channel.basicConsume(QUEUE_NAME, true,deliverCallback, consumerTag ->{});
    }
}
