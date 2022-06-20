package org.ruslan.todo.mc.todo.service;

import org.ruslan.todo.mc.entity.Priority;
import org.ruslan.todo.mc.todo.repo.PriorityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PriorityService {

    private final PriorityRepository repository;

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public List<Priority> findAll(Long userId) {
        return repository.findByUserIdOrderByIdAsc(userId);
    }

    public Priority add(Priority priority) {
        return repository.save(priority);
    }

    public Priority update(Priority priority) {
        return repository.save(priority);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Priority findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Priority> find(String title, Long userId) {
        return repository.findByTitle(title, userId);
    }

}