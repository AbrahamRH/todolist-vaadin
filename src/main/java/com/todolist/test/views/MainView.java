package com.todolist.test.views;

import com.todolist.test.service.TaskService;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    TaskForm taskForm;
    TasksView taskView;

    public MainView(TaskService taskService) {
        this.taskForm = new TaskForm(taskService);
        this.taskView = new TasksView(taskService);
        taskForm.addTaskFormListener(event -> taskView.addTask(event.getTask()));

        HorizontalLayout content = new HorizontalLayout();
        content.add(taskForm);
        content.add(taskView);
        add(content);
    }

}
