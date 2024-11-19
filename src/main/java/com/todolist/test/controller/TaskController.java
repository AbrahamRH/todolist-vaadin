package com.todolist.test.controller;

import com.todolist.test.Entity.Task;
import com.todolist.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value="/task/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        Task task = taskService.getTask(taskId);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping(value="/task", consumes = "application/json")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        task = taskService.save(task);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping(value="/tasks")
    public List<Task> getAllTasks() {
        return taskService.getTasks();
    }

    @PostMapping(value="/task/{taskId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
