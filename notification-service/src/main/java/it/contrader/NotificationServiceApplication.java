package it.contrader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "transactionTopic")
    public void handleTransactionNotification(TransId id){
        System.out.println("ci entro " + id);
    }

    @KafkaListener(topics = "clothesTopic" )
    public void HandleClothesNotification(Object id){
        System.out.println("entro da clothes " + id);
    }
}
