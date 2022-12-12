package src.book.present.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.book.core.entities.UserEntity;
import src.book.core.usecases.UserUseCase;
import src.book.present.convert.UserConvert;
import src.book.present.resources.UserResource;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController extends BaseController {

    private final UserUseCase userUseCase;

    @Autowired
    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
//    @PreAuthorize("hasAnyRole('ADMIN')")
    List<UserResource> getAllUser() {
        List<UserEntity> users = userUseCase.getAll();
        return UserConvert.Cloner.usersEntityToResource(users);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    UserResource getUserById(@PathVariable @Min(1) Long id) {
        UserEntity user = userUseCase.getUserById(id);
        return UserConvert.Cloner.userEntityToResource(user);
    }
}
