package src.book.adapter.models;

import src.book.core.enums.roles;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class userModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    public userModel(String name, String password, roles role) {
        this.username = name;
        this.password = password;
        this.role = role.toString();
    }

    public userModel() {

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

    public String getRole() {
        return role;
    }

    public void setRole(roles role) {
        this.role = role.toString();
    }
}
