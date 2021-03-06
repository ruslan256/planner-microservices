package org.ruslan.todo.mc.todo.controller;

import org.ruslan.todo.mc.entity.Stat;
import org.ruslan.todo.mc.todo.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {

    private final StatService statService;


    public StatController(StatService statService) {
        this.statService = statService;
    }

    @PostMapping("/stat")
    public ResponseEntity<Stat> findByUserId(@RequestBody Long userId) {

        return ResponseEntity.ok(statService.findStat(userId));
    }

}