package src.book.present.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.book.core.entities.TokenEntity;
import src.book.core.entities.UserEntity;
import src.book.core.usecases.InsertUserUseCase;
import src.book.present.convert.TokenConvert;
import src.book.present.convert.UserConvert;
import src.book.present.requests.LoginRequest;
import src.book.present.requests.UserRequest;
import src.book.present.resources.TokenResource;
import src.book.present.resources.UserResource;
import src.book.present.security.AuthUseCase;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController extends BaseController {
    private final AuthUseCase authUseCase;

    private final InsertUserUseCase insertUserUseCase;

    @Autowired
    public AuthController(AuthUseCase authUseCase, InsertUserUseCase insertUserUseCase) {
        this.authUseCase = authUseCase;
        this.insertUserUseCase = insertUserUseCase;
    }

    @GetMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    TokenResource login(@RequestBody @Valid LoginRequest req) {
        TokenEntity token = authUseCase.login(req);
        return TokenConvert.cloner.tokenEntityToResource(token);
    }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.OK)
    UserResource insertUser(@RequestBody @Valid UserRequest userReq) {
        UserEntity user = insertUserUseCase.insertUser(userReq);
        return UserConvert.Cloner.userEntityToResource(user);
    }
}
