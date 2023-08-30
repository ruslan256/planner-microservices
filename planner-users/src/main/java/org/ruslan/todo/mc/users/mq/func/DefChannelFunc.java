package org.ruslan.todo.mc.users.mq.func;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DefChannelFunc {

//    private final Sinks.Many<Message<Long>> innerBus = Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
//
//    @Bean
//    public Supplier<Flux<Message<Long>>> newUserDataActionProduce() {
//        return innerBus::asFlux;
//    }
}