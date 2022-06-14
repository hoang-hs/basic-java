package src.book.api.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class userResource {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;

    public userResource(Long id, String username) {
        this.id = id;
        this.username = username;
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
}
