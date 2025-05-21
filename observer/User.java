
package observer;

import observer.UserObserver;

public class User implements UserObserver {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void notify(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}
