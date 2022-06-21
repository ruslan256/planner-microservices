package org.ruslan.todo.mc.todo.repo;

import org.ruslan.todo.mc.entity.Stat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {

    Stat findByUserId(Long id); // 1 пользователь содержит только 1 строку статистики (связь "один к одному")
}