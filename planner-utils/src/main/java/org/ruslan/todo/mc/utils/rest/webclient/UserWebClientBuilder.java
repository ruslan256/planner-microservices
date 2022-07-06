package org.ruslan.todo.mc.utils.rest.webclient;

import org.ruslan.todo.mc.entity.User;
import org.springframework.web.reactive.function.client.WebClient;

public class UserWebClientBuilder {

    private static final String baseUrl = "http://localhost:8765/planner-users/user/";

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