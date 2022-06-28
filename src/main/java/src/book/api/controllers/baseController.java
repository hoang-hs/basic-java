package src.book.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class baseController {

    @GetMapping("/ping")
    String Ping() {
        return "pong";
    }

    ResponseEntity<Object> responseData(Object data) {
        return ResponseEntity.ok(data);
    }

}