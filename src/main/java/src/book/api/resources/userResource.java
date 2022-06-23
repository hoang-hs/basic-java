package src.book.api.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class userResource {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
    public userResource(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public userResource(Long id, String username, String password) {
        this.id = id;
        this.username = username;
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
