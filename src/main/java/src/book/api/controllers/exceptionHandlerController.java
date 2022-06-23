package src.book.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import src.book.api.resources.errResource;
import src.book.exception.appException;

@RestControllerAdvice
public class exceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<errResource> globalExceptionHandler(Exception ex, WebRequest request) {
        errResource err = new errResource(HttpStatus.INTERNAL_SERVER_ERROR.value(), "system error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(appException.class)
    public ResponseEntity<errResource> handleException(appException ex) {
        errResource err = new errResource(ex.getHttpStatus().value(), ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(err);
    }

}
