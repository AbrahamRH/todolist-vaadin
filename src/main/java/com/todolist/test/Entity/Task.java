package com.todolist.test.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_gen")
    @SequenceGenerator(name = "task_id_gen", sequenceName = "task_id_seq", allocationSize = 1)
    @Column(name="task_id", nullable = false)
    private Long id;

    @Size(min = 1, max = 50)
    @Unique
    @Column(name="name", nullable = false, length = 50, unique = true)
    private String name;

    @Size(min = 1, max = 200)
    @Column(name="description", length = 200)
    private String description;

    @ColumnDefault("false")
    @Column(name="completed")
    private boolean completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

