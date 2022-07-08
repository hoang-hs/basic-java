package src.book.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.book.api.convert.UserConvert;
import src.book.api.requests.UserRequest;
import src.book.api.resources.UserResource;
import src.book.core.entities.UserEntity;
import src.book.core.usecases.GetUserUseCase;
import src.book.core.usecases.InsertUserUseCase;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController extends BaseController {

    private final GetUserUseCase getUserUseCase;
    private final InsertUserUseCase insertUserUseCase;


    @Autowired
    public UserController(GetUserUseCase getUserUseCase, InsertUserUseCase insertUserUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.insertUserUseCase = insertUserUseCase;
    }

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    List<UserResource> getAllUser() {
        List<UserEntity> users = getUserUseCase.getAll();
        return UserConvert.Cloner.usersEntityToResource(users);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    UserResource getUserById(@PathVariable @Min(1) Long id) {
        UserEntity user = getUserUseCase.getUserById(id);
        return UserConvert.Cloner.userEntityToResource(user);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    UserResource insertUser(@RequestBody @Valid UserRequest userReq) {
        UserEntity user = insertUserUseCase.insertUser(userReq);
        return UserConvert.Cloner.userEntityToResource(user);
    }
}
