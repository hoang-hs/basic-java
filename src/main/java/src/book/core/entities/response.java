package src.book.core.entities;

import org.springframework.http.HttpStatus;

public class response {
    private HttpStatus httpCode;
    private String message;
    private Object data;

    public response(HttpStatus httpCode, String message, Object data) {
        this.httpCode = httpCode;
        this.message = message;
        this.data = data;
    }

    public response(HttpStatus httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    public response(Object data) {
        httpCode = HttpStatus.OK;
        this.data = data;
    }

    public boolean isError() {
        return httpCode != HttpStatus.OK;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
