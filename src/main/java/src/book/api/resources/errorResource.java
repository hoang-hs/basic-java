package src.book.api.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class errorResource {
    @JsonProperty("message")
    String Message;

    public errorResource(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
