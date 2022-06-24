package src.book.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.book.api.mappers.userMapper;
import src.book.api.requests.userRequest;
import src.book.core.entities.userEntity;
import src.book.core.usecases.getUserUseCase;
import src.book.core.usecases.insertUserUseCase;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class userController extends baseController {

    private final getUserUseCase getUserUseCase;
    private final insertUserUseCase insertUserUseCase;

    @Autowired
    public userController(getUserUseCase getUserUseCase, insertUserUseCase insertUserUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.insertUserUseCase = insertUserUseCase;
    }

    @GetMapping("")
    ResponseEntity<Object> getAllUser() {
        List<userEntity> users = getUserUseCase.getAll();
        return responseData(userMapper.NewListUsersResource(users));
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getUserById(@PathVariable @Min(0) Long id) {
        userEntity user = getUserUseCase.getUserById(id);
        return responseData(userMapper.NewUserResource(user));
    }

    @PostMapping("")
    ResponseEntity<Object> insertUser(@RequestBody userRequest userReq) {
        userEntity user = insertUserUseCase.insertUser(userReq);
        return responseData(userMapper.NewUserResourceWithPassword(user));
    }

}
