package io.ylab.intensive.task_lecture5.eventsourcing.api;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApiApp {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();

        PersonApi personApi = applicationContext.getBean("personApi", PersonApi.class);
        personApi.savePerson(1L, "Alex", "Pipkin", "Alexovich");
        personApi.savePerson(2L, "Ivan", "Popkin", "Ivanich");
        wait100millis();
        System.out.println("[info] List of persons from DataBase:\n[info]" + personApi.findAll());
        personApi.deletePerson(2L);
        personApi.savePerson(1L, "AlexUpd", "PipkinUpd", "AlexovichUpd");
        wait100millis();
        System.out.println("[info] Result of finding person with person_id = 1:" + personApi.findPerson(1L));
        System.out.println("[info] Result of finding person with person_id = 2:" + personApi.findPerson(2L));

        applicationContext.close();
    }

    private static void wait100millis() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

