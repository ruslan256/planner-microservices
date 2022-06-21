package org.ruslan.todo.mc.todo.service;

import org.ruslan.todo.mc.entity.Stat;
import org.ruslan.todo.mc.todo.repo.StatRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatService {

    private final StatRepository repository;

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findStat(Long userId) {
        return repository.findByUserId(userId);
    }

}