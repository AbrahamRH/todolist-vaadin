package com.todolist.test.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class TaskCard {

    public TaskCard(String name,String description,boolean completed) {
        VerticalLayout card = new VerticalLayout();
        Text nameText = new Text(name);
        Text descriptionText = new Text(description);
        Checkbox completedCheckbox = new Checkbox("Completed");
        completedCheckbox.setValue(completed);
        card.add(nameText, descriptionText, completedCheckbox);
    }
}
