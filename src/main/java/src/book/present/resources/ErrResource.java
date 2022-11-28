package src.book.present.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrResource {

    @JsonProperty("http_code")
    private int httpCode;

    @JsonProperty("message")
    private String message;

    public ErrResource(int httpCode, String message) {
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
