package com.todolist.test.views;

import com.todolist.test.Entity.Task;
import com.todolist.test.service.TaskService;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Tag("tasks-view")
public class TasksView extends Div {
    private final TaskService taskService;
    private final VerticalLayout cards = new VerticalLayout();

    public TasksView(TaskService taskService) {
        this.taskService = taskService;
        VerticalLayout cardList = new VerticalLayout();
        cardList.setSizeFull();
        updateTasks();
        cardList.add(cards);
        add(cardList);
    }

    private void updateTasks() {
        cards.removeAll();
        taskService.getTasks().forEach(task -> cards.add(new TaskCard(task.getName(), task.getDescription(), task.isCompleted(), this::updateTaskStatus)));
    }

    private void updateTaskStatus(String name, boolean completed) {
        Task taskToUpdate = taskService.findByName(name);
        if(taskToUpdate != null) {
            taskToUpdate.setCompleted(completed);
            taskService.updateTask(taskToUpdate);
        }
    }

    public void addTask(Task task) {
        cards.add(new TaskCard(task.getName(), task.getDescription(), task.isCompleted(), this::updateTaskStatus));
    }

}
