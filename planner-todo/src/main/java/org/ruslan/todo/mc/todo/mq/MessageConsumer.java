package org.ruslan.todo.mc.todo.mq;

import org.ruslan.todo.mc.todo.service.UserInitDataService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(InputBinding.class)
public class MessageConsumer {

    private final UserInitDataService userInitDataService;

    public MessageConsumer(UserInitDataService userInitDataService) {
        this.userInitDataService = userInitDataService;
    }

    @StreamListener(target = InputBinding.INPUT_CHANNEL)
    public void initTestData(Long userId) {
        userInitDataService.initUserData(userId);
    }
}