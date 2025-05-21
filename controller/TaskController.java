
package controller;

import service.TaskService;
import model.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService service) {
        this.taskService = service;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Task\n2. View Tasks\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Title: ");
                String title = scanner.nextLine();
                System.out.print("Description: ");
                String desc = scanner.nextLine();
                System.out.print("Deadline (yyyy-MM-ddTHH:mm): ");
                String deadlineStr = scanner.nextLine();

                LocalDateTime deadline = LocalDateTime.parse(deadlineStr);
                Task task = taskService.createTask(title, desc, deadline);
                System.out.println("Created Task: " + task.getId());
            } else if (choice == 2) {
                List<Task> tasks = taskService.getAllTasks();
                for (Task t : tasks) {
                    System.out.println(t.getId() + " | " + t.getTitle() + " | " +
                        (t.isCompleted() ? "Done" : "Pending") + " | Deadline: " + t.getDeadline());
                }
            } else {
                break;
            }
        }
    }
}

