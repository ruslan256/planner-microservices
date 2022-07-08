package org.ruslan.todo.mc.utils.rest.webclient;

import org.ruslan.todo.mc.entity.User;
import org.ruslan.todo.mc.utils.rest.api.IUserServiceClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component(value = "webClient")
public class UserWebClientBuilder implements IUserServiceClient {

    @Override
    public boolean userExists(Long userId) {

        try {
            User user = WebClient.create(baseUrl)
                    .post()
                    .uri("id")
                    .bodyValue(userId)
                    .retrieve()
                    .bodyToFlux(User.class)
                    .blockFirst();                 // synchronize call

            if (user != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}