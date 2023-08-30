package org.ruslan.todo.mc.todo.kafka;

import org.ruslan.todo.mc.todo.service.UserInitDataService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerKafka {

    private final UserInitDataService userInitDataService;

    public MessageConsumerKafka(UserInitDataService userInitDataService) {
        this.userInitDataService = userInitDataService;
    }

    @KafkaListener(topics = "javabegin-test")
    public void makeTestData(Long userId) throws Exception {
        if (userId > 20030) {
            userInitDataService.initUserData(userId);
            System.out.println("Made new user ID: " + userId);
        } else {
            throw new Exception("Active Dead-Letter-Queue for user ID <= 20030");
        }
    }}