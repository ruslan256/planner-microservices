package org.ruslan.todo.mc.users.mq.func;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class MessageActionsFunc {

    private final DefChannelFunc defChannelFunc;

    public MessageActionsFunc(DefChannelFunc defChannelFunc) {
        this.defChannelFunc = defChannelFunc;
    }

    // send a message
    public void sendNewUserMessage(Long id) {
//        // add a new message to the listener
//        defChannelFunc.getInnerBus().emitNext(MessageBuilder.withPayload(id).build(), Sinks.EmitFailureHandler.FAIL_FAST);
//        log.info("Message sent: {}", id);
    }
}