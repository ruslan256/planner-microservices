package org.ruslan.todo.mc.utils.rest.api;

public interface IUserServiceClient {

    String baseUrl = "http://localhost:8765/planner-users/user/";
    boolean userExists(Long userId);

}