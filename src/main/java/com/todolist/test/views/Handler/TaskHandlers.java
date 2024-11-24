package com.todolist.test.views.Handler;

import com.todolist.test.Entity.Task;

public interface TaskHandlers {
    Task onTaskCreate(String name, String description);
    boolean onTaskChange(String pastName, String newName, String description);
    void onTaskDelete(String name);
    void onTaskCheckboxChange(String name, boolean isComplete);
}
