package src.book.present.requests;

import src.book.present.validate.RoleAnnotation;

import javax.validation.constraints.NotBlank;

public class UserRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
    @RoleAnnotation(message = "role invalid")
    String role;

    public UserRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
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

}
