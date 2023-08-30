package org.ruslan.todo.mc.todo.mq.func;

import org.ruslan.todo.mc.todo.service.UserInitDataService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefChannelConsumerFunc {

    private final UserInitDataService userInitDataService;

    public DefChannelConsumerFunc(UserInitDataService userInitDataService) {
        this.userInitDataService = userInitDataService;
    }

//    @Bean
//    public Consumer<Message<Long>> newUserDataActionConsume() {
//        return message -> userInitDataService.initUserData(message.getPayload());
//    }
}