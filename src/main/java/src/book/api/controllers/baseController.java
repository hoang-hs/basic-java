package src.book.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.book.api.resources.errorResource;
import src.book.core.entities.response;

@RestController
@RequestMapping("/")
public class baseController {

    @GetMapping("/ping")
    String Ping() {
        return "pong";
    }

    ResponseEntity<Object> responseError(response res) {
        return ResponseEntity.status(res.getHttpCode()).body(
                new errorResource(res.getMessage())
        );
    }

    ResponseEntity<Object> responseData(Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}