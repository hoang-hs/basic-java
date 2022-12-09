package src.book.present.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.book.core.entities.TokenEntity;
import src.book.core.entities.UserEntity;
import src.book.core.usecases.AuthUseCase;
import src.book.present.convert.TokenConvert;
import src.book.present.convert.UserConvert;
import src.book.present.requests.LoginRequest;
import src.book.present.requests.UserRequest;
import src.book.present.resources.TokenResource;
import src.book.present.resources.UserResource;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController extends BaseController {
    private final AuthUseCase authUseCase;

    @Autowired
    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @GetMapping("/signin")
    @ResponseStatus(value = HttpStatus.OK)
    TokenResource signin(@RequestBody @Valid LoginRequest req) {
        TokenEntity token = authUseCase.signin(req);
        return TokenConvert.cloner.tokenEntityToResource(token);
    }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.OK)
    UserResource signup(@RequestBody @Valid UserRequest userReq) {
        UserEntity user = authUseCase.signup(userReq);
        return UserConvert.Cloner.userEntityToResource(user);
    }
}
