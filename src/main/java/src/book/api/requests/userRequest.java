package src.book.api.requests;

import src.book.api.validate.roleAnnotation;
import src.book.core.enums.roles;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public class userRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
    @roleAnnotation
    String role;

    public userRequest(String username, String password, String role) {
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
