package org.ruslan.todo.mc.utils.rest.webclient;

import org.ruslan.todo.mc.utils.rest.api.IDataServiceClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class LoadDataWebClient implements IDataServiceClient {

    @Override
    public Flux<Boolean> initUserDataAsync(Long userId) {

        return WebClient.create(baseUrlData)
                .post()
                .uri("init")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(Boolean.class);        // asynchronous call
    }
}