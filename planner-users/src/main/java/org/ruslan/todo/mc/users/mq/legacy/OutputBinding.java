package org.ruslan.todo.mc.users.mq.legacy;

//import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputBinding {

    String OUTPUT_CHANNEL = "todoOutputChannel";

    // create Channel to send messages
    //@Output(OUTPUT_CHANNEL)
    MessageChannel todoOutputChannel();

}