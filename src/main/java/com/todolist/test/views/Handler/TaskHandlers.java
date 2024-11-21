package com.todolist.test.views.Handler;

import com.todolist.test.Entity.Task;

public interface TaskHandlers {
    Task onTaskCreate(String name, String description);
    void onTaskChange(String name, String description);
    void onTaskDelete(String name);
}
