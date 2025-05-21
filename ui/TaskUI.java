package ui;

import model.Task;
import service.TaskService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class TaskUI extends JFrame {
    private TaskService service;
    private DefaultTableModel tableModel;

    public TaskUI(TaskService service) {
        this.service = service;
        setTitle("To-Do App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Task table
        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Status", "Deadline"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        JTextField titleField = new JTextField();
        JTextField descField = new JTextField();
        JTextField deadlineField = new JTextField(); // Format: 2025-05-21T23:00

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(descField);
        formPanel.add(new JLabel("Deadline (yyyy-MM-ddTHH:mm):"));
        formPanel.add(deadlineField);

        JButton addBtn = new JButton("Add Task");
        formPanel.add(addBtn);

        add(formPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            try {
                String title = titleField.getText();
                String desc = descField.getText();
                LocalDateTime deadline = LocalDateTime.parse(deadlineField.getText());

                Task task = service.createTask(title, desc, deadline);
                JOptionPane.showMessageDialog(this, "Task Added");

                updateTable();
                titleField.setText("");
                descField.setText("");
                deadlineField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        List<Task> tasks = service.getAllTasks();
        for (Task t : tasks) {
            tableModel.addRow(new Object[]{
                t.getId(), t.getTitle(),
                t.isCompleted() ? "Done" : "Pending",
                t.getDeadline()
            });
        }
    }
}
