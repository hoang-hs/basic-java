package src.book.adapter.models;

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

    public userModel() {

    }

    public userModel(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public userModel(String name, String password) {
        this.username = name;
        this.password = password;
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


}
