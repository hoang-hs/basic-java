package src.book.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.book.api.convert.userConvert;
import src.book.api.requests.userRequest;
import src.book.api.resources.userResource;
import src.book.core.entities.userEntity;
import src.book.core.usecases.getUserUseCase;
import src.book.core.usecases.insertUserUseCase;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class userController extends baseController {

    private final getUserUseCase getUserUseCase;
    private final insertUserUseCase insertUserUseCase;

    private final userConvert userConvert;

    @Autowired
    public userController(getUserUseCase getUserUseCase, insertUserUseCase insertUserUseCase, src.book.api.convert.userConvert userConvert) {
        this.getUserUseCase = getUserUseCase;
        this.insertUserUseCase = insertUserUseCase;
        this.userConvert = userConvert;
    }

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    List<userResource> getAllUser() {
        List<userEntity> users = getUserUseCase.getAll();
        return userConvert.convertListUsersEntityToResource(users);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    userResource getUserById(@PathVariable @Min(1) Long id) {
        userEntity user = getUserUseCase.getUserById(id);
        return userConvert.convertUserEntityToResource(user);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    userResource insertUser(@RequestBody @Valid userRequest userReq) {
        userEntity user = insertUserUseCase.insertUser(userReq);
        return userConvert.convertUserEntityToResource(user);
    }

}
