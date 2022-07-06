package src.book.exception;

import org.springframework.http.HttpStatus;

public class systemErrorException {

    public static appException Default() {
        return new appException("system error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static appException WithMessage(String msg) {
        return new appException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
