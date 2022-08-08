package org.ruslan.todo.mc.todo.mq.func;

import org.ruslan.todo.mc.todo.service.UserInitDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class DefChannelConsumerFunc {

    private final UserInitDataService userInitDataService;

    public DefChannelConsumerFunc(UserInitDataService userInitDataService) {
        this.userInitDataService = userInitDataService;
    }

    @Bean
    public Consumer<Message<Long>> newUserActionConsume() {
        return message -> userInitDataService.initUserData(message.getPayload());
    }
}