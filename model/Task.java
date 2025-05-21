package model;

import java.time.LocalDateTime;

public class Task {
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime deadline;

    public Task(String id, String title, String description, LocalDateTime deadline) {
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
    public LocalDateTime getDeadline() { return deadline; }

    public void markComplete() { this.completed = true; }
}
