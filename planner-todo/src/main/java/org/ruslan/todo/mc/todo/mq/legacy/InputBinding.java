package org.ruslan.todo.mc.todo.mq.legacy;

//import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface InputBinding {

    String INPUT_CHANNEL = "todoInputChannel";

    //@Input(INPUT_CHANNEL)
    MessageChannel todoInputChannel();
}