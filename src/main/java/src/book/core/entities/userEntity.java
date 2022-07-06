package src.book.core.entities;

import src.book.core.enums.roles;

public class userEntity {
    private Long id;
    private String username;
    private String password;
    roles role;
    public userEntity() {
    }
    public userEntity(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = roles.fromString(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public roles getRole() {
        return role;
    }

    public void setRole(roles role) {
        this.role = role;
    }
}
