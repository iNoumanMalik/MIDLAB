
package service;

import model.Task;
import repository.TaskRepository;
import observer.UserObserver;

import java.time.LocalDateTime;
import java.util.*;

public class TaskService {
    private TaskRepository repo = new TaskRepository();
    private List<UserObserver> observers = new ArrayList<>();

    public Task createTask(String title, String description, LocalDateTime deadline) {
        String id = UUID.randomUUID().toString();
        Task task = new Task(id, title, description, deadline);
        repo.save(task);

        if (deadline.isBefore(LocalDateTime.now().plusHours(1))) {
            notifyObservers("Task '" + title + "' is near its deadline!");
        }

        return task;
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public void registerObserver(UserObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (UserObserver obs : observers) {
            obs.notify(message);
        }
    }
}

