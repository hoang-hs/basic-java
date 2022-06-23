package src.book.exception;

import org.springframework.http.HttpStatus;

public class resourceNotFoundException {
    public static appException Default() {
        return new appException("resource not found", HttpStatus.NOT_FOUND);
    }

    public static appException WithMessage(String msg) {
        return new appException(msg, HttpStatus.NOT_FOUND);
    }
}
