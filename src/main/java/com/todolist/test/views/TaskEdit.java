package com.todolist.test.views;

import com.todolist.test.views.Handler.TaskHandlersImpl;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class TaskEdit extends Dialog {
    private final TaskHandlersImpl taskHandlers;
    private final TextField name = new TextField("Name");
    private final TextField description = new TextField("Description");
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");
    private final String taskName;

    public TaskEdit(TaskHandlersImpl taskHandlers, String taskName) {
        addClassName("task-edit-dialog");
        this.taskHandlers = taskHandlers;
        this.taskName = taskName;
        addSaveButtonAction();
        VerticalLayout layout = new VerticalLayout();
        layout.add(name, description, buttonsConfig());
        add(layout);
    }


    private HorizontalLayout buttonsConfig() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        cancel.addClickListener(click -> this.close());
        return new HorizontalLayout(save, cancel);
    }

    private void addSaveButtonAction(){
        save.addClickListener(click -> {
            String name = this.name.getValue();
            String description = this.description.getValue();
            boolean hasChange = taskHandlers.onTaskChange(taskName, name, description);
            this.close();
            if(hasChange) {
                Notification notification = Notification.show("Task submitted");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } else {
                Notification notification = Notification.show("Task not submitted");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

    }

}
