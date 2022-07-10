package org.ruslan.todo.mc.utils.rest.webclient;

import org.ruslan.todo.mc.entity.User;
import org.ruslan.todo.mc.utils.rest.api.IUserServiceClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component(value = "webClient")
public class UserWebClientBuilder implements IUserServiceClient {

    @Override
    public boolean userExists(Long userId) {

        try {
            User user = WebClient.create(baseUrlUser)
                    .post()
                    .uri("id")
                    .bodyValue(userId)
                    .retrieve()
                    .bodyToFlux(User.class)
                    .blockFirst();                 // synchronous call

            if (user != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Flux<User> userExistsAsync(Long userId) {

        return WebClient.create(baseUrlUser)
                .post()
                .uri("id")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(User.class);           // asynchronous call
    }
}