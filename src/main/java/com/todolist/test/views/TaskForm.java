package com.todolist.test.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


public class TaskForm extends FormLayout {
    TextField name = new TextField("Name");
    TextField description = new TextField("Description");
    Button save = new Button("Save");
    Button cancel = new Button("Cancel");

    public TaskForm() {
        addClassName("task-form");
        VerticalLayout layout = new VerticalLayout();
        layout.add(name, description, createButtonsLayout());
        add(layout);
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, cancel);
    }
}
