package src.book.api.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import src.book.core.entities.userEntity;

public class userResource {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;

    public userResource() {
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
