package org.ruslan.todo.mc.todo.feign;

import org.ruslan.todo.mc.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="planner-users", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @PostMapping("/user/id")
    ResponseEntity<User> findUserById(@RequestBody Long id);

}

@Component
class UserFeignClientFallback implements UserFeignClient {

    // this method will be called, if the service '/user/id' will be not available
    @Override
    public ResponseEntity<User> findUserById(Long id) {
        return null;
    }
}