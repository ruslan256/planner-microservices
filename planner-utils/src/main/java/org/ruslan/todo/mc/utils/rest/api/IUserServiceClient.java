package org.ruslan.todo.mc.utils.rest.api;

import org.ruslan.todo.mc.entity.User;
import reactor.core.publisher.Flux;

public interface IUserServiceClient {

    String baseUrlUser = "http://localhost:8765/planner-users/user/";
    boolean userExists(Long userId);

    Flux<User> userExistsAsync(Long userId);
}