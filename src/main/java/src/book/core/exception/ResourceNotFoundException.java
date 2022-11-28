package src.book.core.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException {

    private static final AppException resourceNotFoundException =
            new AppException("resource not found", HttpStatus.NOT_FOUND);
//    public static AppException Default() {
//        return new AppException("resource not found", HttpStatus.NOT_FOUND);
//    }

    public static AppException Default() {
        return resourceNotFoundException;
    }

    public static AppException WithMessage(String msg) {
        return new AppException(msg, HttpStatus.NOT_FOUND);
    }
}
