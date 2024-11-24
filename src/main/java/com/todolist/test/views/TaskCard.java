package com.todolist.test.views;

import com.todolist.test.views.Handler.TaskHandlersImpl;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Tag("task-card")
public class TaskCard extends Div {

    VerticalLayout mainContent = new VerticalLayout();
    VerticalLayout buttons = new VerticalLayout();
    Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
    Button editButton = new Button(new Icon(VaadinIcon.EDIT));
    Checkbox completedCheckbox = new Checkbox("Completed");
    TaskEdit editDialog;

    public TaskCard(String name, String description, boolean completed, TaskHandlersImpl tasksHandlers) {
        addClassName("task-card");
        editDialog = new TaskEdit(tasksHandlers, name);
        HorizontalLayout card = new HorizontalLayout();
        configMainContent(name, description, completed, tasksHandlers);
        configButtons(name, description, tasksHandlers);
        card.add(mainContent, buttons);
        card.setAlignItems(FlexComponent.Alignment.CENTER);
        add(card);
    }

    private void configMainContent(String name, String description, boolean completed, TaskHandlersImpl taskHandlers) {
        Span cardTitle = new Span(name);
        cardTitle.addClassName("name");
        Span cardDescription = new Span(description);
        cardDescription.addClassName("description");
        mainContent.addClassName("main-content-card");
        completedCheckbox.setValue(completed);
        completedCheckbox.addValueChangeListener(event -> {
            boolean changeStatus =  event.getValue();
            taskHandlers.onTaskCheckboxChange(name, changeStatus);
        });
        mainContent.add(cardTitle, cardDescription, completedCheckbox);
    }

    private void configButtons(String name, String description, TaskHandlersImpl tasksHandlers) {
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteButton.setAriaLabel("Delete");
        deleteButton.addClickListener(_ -> tasksHandlers.onTaskDelete(name));

        editButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        editButton.setAriaLabel("Edit");
        editButton.addClickListener(_ -> editDialog.open());

        buttons.addClassName("buttons-card");
        buttons.add(deleteButton, editButton);
    }


}
