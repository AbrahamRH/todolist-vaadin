package com.todolist.test.persistance;

import com.todolist.test.Entity.Task;
import jakarta.validation.constraints.Size;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task save(Task task);

    Task findTaskById(Long id);

    List<Task> findAll();

    void deleteById(Long id);

    Task findTaskByName(@Size(min = 1, max = 50) @Unique String name);
}
