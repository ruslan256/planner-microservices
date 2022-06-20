package org.ruslan.todo.mc.todo.repo;

import org.ruslan.todo.mc.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // search for all category for the selected user
    List<Category> findByUserIdOrderByTitleAsc(Long id);

    // search values by title for the selected user
    @Query("SELECT c FROM Category c where " +
            "(:title is null or :title='' "  +
            " or lower(c.title) like lower(concat('%', :title,'%'))) " +
            " and c.userId=:id    " +
            " order by c.title asc")
    List<Category> findByTitle(@Param("title") String title, @Param("id") Long id);

}