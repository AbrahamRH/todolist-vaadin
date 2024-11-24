package com.todolist.test.views.Handler;

import com.todolist.test.Entity.Task;
import com.todolist.test.service.TaskService;

import java.util.ArrayList;
import java.util.List;

public class TaskHandlersImpl implements TaskHandlers {
    TaskService taskService;
    private final List<Runnable> deleteListeners = new ArrayList<>();

    public TaskHandlersImpl(TaskService taskService) {
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
    public boolean onTaskChange(String pastName, String newName, String description) {
        boolean pastNameExists = taskService.findByName(pastName) != null;
        if(!newName.isEmpty() && pastNameExists) {
            Task task = Task.builder().name(newName).description(description).build();
             boolean hasChange = taskService.updateTask(pastName, task) > 0;
            notifyUpdateCardsListeners();
            return hasChange;
        } else {
            return false;
        }
    }

    @Override
    public void onTaskDelete(String name) {
        Task task = taskService.findByName(name);
        if(task != null) {
            taskService.deleteTask(task.getId());
            notifyUpdateCardsListeners();
        }
    }

    @Override
    public void onTaskCheckboxChange(String name, boolean isComplete) {
        Task taskToUpdate = taskService.findByName(name);
        if(taskToUpdate != null) {
            taskToUpdate.setCompleted(isComplete);
            taskService.updateTask(name, taskToUpdate);
        }
    }

    public void addDeleteListener(Runnable listener) {
        deleteListeners.add(listener);
    }

    private void notifyUpdateCardsListeners() {
        deleteListeners.forEach(Runnable::run);
    }
}
