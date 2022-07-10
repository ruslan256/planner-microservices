package org.ruslan.todo.mc.utils.rest.resttemplate;

import org.ruslan.todo.mc.entity.User;
import org.ruslan.todo.mc.utils.rest.api.IUserServiceClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component(value = "restTemplate")
public class UserRestBuilder implements IUserServiceClient {

    @Override
    public boolean userExists(Long userId) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> request = new HttpEntity(userId);

        try {
            ResponseEntity<User> response = restTemplate.exchange(baseUrlUser + "id", HttpMethod.POST, request, User.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Flux<User> userExistsAsync(Long userId) {
        return Flux.from(Mono.empty());
    }

}