package com.todolist.test.views.Handler;

@FunctionalInterface
public interface TaskCompleteHandler {
    void onTaskCompleteChange(String name, boolean isComplete);

}
