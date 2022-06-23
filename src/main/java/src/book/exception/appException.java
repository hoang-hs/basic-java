package src.book.exception;

import org.springframework.http.HttpStatus;

public class appException extends RuntimeException {
    private HttpStatus httpStatus;

    public appException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
