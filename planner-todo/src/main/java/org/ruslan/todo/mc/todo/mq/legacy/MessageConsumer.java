package org.ruslan.todo.mc.todo.mq.legacy;

import org.ruslan.todo.mc.todo.service.UserInitDataService;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.stereotype.Component;

//@Component
//@EnableBinding(InputBinding.class)
public class MessageConsumer {

    private final UserInitDataService userInitDataService;

    public MessageConsumer(UserInitDataService userInitDataService) {
        this.userInitDataService = userInitDataService;
    }

//    @StreamListener(target = InputBinding.INPUT_CHANNEL)
    public void initTestData(Long userId) throws Exception {
        if (userId > 20030) {
            userInitDataService.initUserData(userId);
        } else {
            throw new Exception("Active Dead-Letter-Queue for user ID <= 20030");
        }
    }
}