package com.todolist.test.views;

import com.todolist.test.Entity.Task;
import com.todolist.test.service.TaskService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private TaskService taskService;
    TaskForm taskForm = new TaskForm();
    TasksView taskList = new TasksView();
    Grid<Task> taskGrid = new Grid<>(Task.class);


    public MainView(TaskService taskService) {
        this.taskService = taskService;

        HorizontalLayout content = new HorizontalLayout();
        content.add(taskForm);
        content.add(taskGrid);
        configureGrid();

        add(content, taskGrid);
        updateGrid();
    }

    private void updateGrid() {
        taskGrid.setItems(taskService.getTasks());
    }

    private void configureGrid() {
        taskGrid.setColumns("name", "description");
    }

}
