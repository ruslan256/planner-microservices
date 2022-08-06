package org.ruslan.todo.mc.users.mq.legacy;

//import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;

//@Component
//@EnableBinding(OutputBinding.class)
public class MessageProducer {

    private final OutputBinding outputBinding;

    public MessageProducer(OutputBinding outputBinding) {
        this.outputBinding = outputBinding;
    }

    // send message if create a new user
    public void initUserData(Long userId) {

        // container to add data and headers
        Message<Long> message = MessageBuilder.withPayload(userId).build();

        outputBinding.todoOutputChannel().send(message); // choose channel and send message
    }
}