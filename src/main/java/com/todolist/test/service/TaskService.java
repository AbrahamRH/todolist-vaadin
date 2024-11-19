package com.todolist.test.service;

import com.todolist.test.Entity.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);
    void deleteTask(Long id);
    Task getTask(Long id);
    List<Task> getTasks();
}
