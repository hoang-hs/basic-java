package src.book.exception;

import org.springframework.http.HttpStatus;

public class badRequestException {
    public static appException Default() {
        return new appException("invalid request", HttpStatus.BAD_REQUEST);
    }

    public static appException WithMessage(String msg) {
        return new appException(msg, HttpStatus.BAD_REQUEST);
    }
}
