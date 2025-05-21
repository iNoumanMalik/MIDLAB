
package midlab;

import observer.User;
import service.TaskService;
import ui.TaskUI;

import javax.swing.*;

public class MIDLAB {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        service.registerObserver(new User("Ali"));
        service.registerObserver(new User("Fatima"));

        SwingUtilities.invokeLater(() -> {
            TaskUI ui = new TaskUI(service);
            ui.setVisible(true);
        });
    }
}
