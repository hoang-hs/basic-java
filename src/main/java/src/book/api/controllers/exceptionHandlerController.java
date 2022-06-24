package src.book.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import src.book.api.resources.errResource;
import src.book.core.usecases.getUserUseCase;
import src.book.exception.appException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class exceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(getUserUseCase.class);


    @ExceptionHandler(appException.class)
    public ResponseEntity<errResource> handleAppException(appException ex) {
        errResource err = new errResource(ex.getHttpStatus().value(), ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<errResource> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        logger.warn("msg: [{}], request description: [{}]", ex.getMessage(), request.getDescription(false));
        ex.printStackTrace();
        errResource err = new errResource(HttpStatus.BAD_REQUEST.value(), "invalid request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<errResource> globalExceptionHandler(Exception ex, WebRequest request) {
        logger.error("msg: {}, request description: {}", ex.getMessage(), request.getDescription(false));
        ex.printStackTrace();
        errResource err = new errResource(HttpStatus.INTERNAL_SERVER_ERROR.value(), "system error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

}
