package src.book.exception;

import org.springframework.http.HttpStatus;

public class unauthorizedException {
    public static appException Default() {
        return new appException("unauthorized", HttpStatus.UNAUTHORIZED);
    }

    public static appException WithMessage(String msg) {
        return new appException(msg, HttpStatus.UNAUTHORIZED);
    }
}
