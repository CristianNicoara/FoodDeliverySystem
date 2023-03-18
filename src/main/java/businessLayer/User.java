package businessLayer;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class User implements Serializable {
    private String username;
    private String password;
    private String role;
    private int id;
    private static final AtomicInteger count = new AtomicInteger(0);

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = count.incrementAndGet();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

}
