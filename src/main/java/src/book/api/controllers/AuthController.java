package src.book.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.book.api.convert.TokenConvert;
import src.book.api.requests.LoginRequest;
import src.book.api.resources.TokenResource;
import src.book.core.entities.TokenEntity;
import src.book.core.usecases.AuthUseCase;

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

    @GetMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    TokenResource login(@RequestBody @Valid LoginRequest req) {
        TokenEntity token = authUseCase.login(req);
        return TokenConvert.cloner.tokenEntityToResource(token);
    }
}
