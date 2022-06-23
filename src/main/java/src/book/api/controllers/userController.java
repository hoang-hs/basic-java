package src.book.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.book.core.entities.user;
import src.book.api.mappers.userMapper;
import src.book.core.usecases.getUserUseCase;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController extends baseController {

    private final getUserUseCase getUserUseCase;

    @Autowired
    public userController(getUserUseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }

    @GetMapping("")
    ResponseEntity<Object> getAllUser() {
        List<user> users = getUserUseCase.getAll();
        return responseData(userMapper.NewListUsersResource(users));
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getUserById(@PathVariable Long id) {
        user user = getUserUseCase.getUserById(id);
        return responseData(userMapper.NewUserResource(user));
    }

}
