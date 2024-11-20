package com.todolist.test.views;

import com.todolist.test.views.Handler.TaskCompleteHandler;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Tag("task-card")
public class TaskCard extends Div {
    public TaskCard(String name,String description,boolean completed, TaskCompleteHandler completeHandler) {
        addClassName("task-card");
        VerticalLayout card = new VerticalLayout();
        Span cardTitle = new Span(name);
        cardTitle.addClassName("name");
        Span cardDescription = new Span(description);
        cardDescription.addClassName("description");
        Checkbox completedCheckbox = new Checkbox("Completed");
        completedCheckbox.setValue(completed);
        completedCheckbox.addValueChangeListener(event -> {
            boolean changeStatus =  event.getValue();
            completeHandler.onTaskCompleteChange(name, changeStatus);
        });
        card.add(cardTitle, cardDescription, completedCheckbox);

        add(card);
    }


}
