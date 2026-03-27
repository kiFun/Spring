package org.example.notificationservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    final EmailService service;

    public Consumer(EmailService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = {"user-events"},
            groupId = "notification-service",
            containerFactory = "userEventContainerFactory"
    )
    public void consumeUserEvent(UserEvent event){
        System.out.println(1);
        switch (event.getType()){
            case CREATE -> service.sendCreated(event.getEmail());
            case DELETE -> service.sendDeleted(event.getEmail());
        }
    }
}
