package org.ruslan.todo.mc.todo.controller;

import org.ruslan.todo.mc.todo.service.UserInitDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class SetInitDataController {

    private final UserInitDataService userInitDataService;

    public SetInitDataController(UserInitDataService userInitDataService) {
        this.userInitDataService = userInitDataService;
    }

    @PostMapping("/init")
    public ResponseEntity<Boolean> init(@RequestBody Long userId) {

        userInitDataService.initUserData(userId);

        return ResponseEntity.ok(true);
    }
}