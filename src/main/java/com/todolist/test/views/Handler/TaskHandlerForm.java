package com.todolist.test.views.Handler;

import com.todolist.test.Entity.Task;
import com.todolist.test.service.TaskService;

public class TaskHandlerForm implements TaskHandlers {
    TaskService taskService;

    public TaskHandlerForm(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public Task onTaskCreate(String name, String description) {
        if(!name.isEmpty()) {
            Task task = Task.builder().name(name).description(description).build();
            return taskService.save(task);
        } else {
            return null;
        }
    }

    @Override
    public void onTaskChange(String name, String description) {

    }

    @Override
    public void onTaskDelete(String name) {

    }
}
