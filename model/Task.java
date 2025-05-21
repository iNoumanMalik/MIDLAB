package model;

import java.time.LocalDate; 

public class Task {
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate deadline;

    public Task(String id, String title, String description, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.completed = false;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public LocalDate getDeadline() { return deadline; }

    public void markComplete() { this.completed = true; }
}
