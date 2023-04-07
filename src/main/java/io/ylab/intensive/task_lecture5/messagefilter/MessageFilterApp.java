package io.ylab.intensive.task_lecture5.messagefilter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

public class MessageFilterApp {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        DbControllerImpl dbController = applicationContext.getBean("dbController", DbControllerImpl.class);
        dbController.initDb("obscene_words");
        File inputFile = new File("src/main/java/io/ylab/intensive/lesson05/messagefilter/words.txt");
        dbController.fillDbFromFile(inputFile);
        MessageProcessor messageProcessor = applicationContext.getBean("messageProcessor", MessageProcessor.class);
        messageProcessor.waitForMessagesAndProcessIt();
    }



}
