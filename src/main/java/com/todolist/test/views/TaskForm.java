package com.todolist.test.views;

import com.todolist.test.Entity.Task;
import com.todolist.test.service.TaskService;
import com.todolist.test.views.Handler.TaskHandlerForm;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


public class TaskForm extends FormLayout {

    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");

    public TaskForm(TaskService taskService) {
        addClassName("task-form");

        TaskHandlerForm taskHandlerForm = new TaskHandlerForm(taskService);
        VerticalLayout layout = new VerticalLayout();
        layout.add(name, description, createButtonsLayout());
        addSaveButtonConfig(taskHandlerForm);
        addCancelButtonConfig();
        add(layout);
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, cancel);
    }

    private void addSaveButtonConfig(TaskHandlerForm taskHandlerForm){
        save.addClickListener(click -> createTask(taskHandlerForm));
    }

    private void createTask(TaskHandlerForm taskHandlerForm){
        String taskName = this.name.getValue();
        String taskDescription = this.description.getValue();
        Task task = taskHandlerForm.onTaskCreate(taskName, taskDescription);
        name.clear();
        description.clear();
        if(task != null) {
            Notification notification = Notification.show("Task submitted");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            fireEvent(new TaskFormEvent(this, task));
        } else {
            Notification notification = Notification.show("Task not submitted");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    private void addCancelButtonConfig(){
        cancel.addClickListener(click -> {
            name.clear();
            description.clear();
        });
    }

    public static class TaskFormEvent extends ComponentEvent<TaskForm> {
        private Task task;
        public TaskFormEvent(TaskForm source, Task task) {
            super(source, false);
            this.task = task;
        }
        public Task getTask() {
            return task;
        }
    }

    public void addTaskFormListener(ComponentEventListener<TaskFormEvent> listener) {
        addListener(TaskFormEvent.class, listener);
    }
}
