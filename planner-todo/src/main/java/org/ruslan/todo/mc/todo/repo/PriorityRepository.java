package org.ruslan.todo.mc.todo.repo;

import org.ruslan.todo.mc.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    // search for all values for the selected user
    List<Priority> findByUserIdOrderByIdAsc(Long id);

    // search values by title for the selected user
    @Query("SELECT p FROM Priority p where " +
            "(:title is null or :title='' "  +
            " or lower(p.title) like lower(concat('%', :title,'%'))) " +
            " and p.userId=:id " +
            "order by p.title asc")
    List<Priority> findByTitle(@Param("title") String title, @Param("id") Long id);

}