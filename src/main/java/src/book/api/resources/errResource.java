package src.book.api.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class errResource {

    @JsonProperty("http_code")
    private int httpCode;

    @JsonProperty("message")
    private String message;

    public errResource(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
