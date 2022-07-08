package src.book.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.book.api.convert.TokenConvert;
import src.book.api.requests.UserRequest;
import src.book.api.resources.TokenResource;
import src.book.core.entities.TokenEntity;
import src.book.core.usecases.AuthUseCase;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController extends baseController {
    private final AuthUseCase authUseCase;

    private final TokenConvert tokenConvert;

    @Autowired
    public AuthController(AuthUseCase authUseCase, TokenConvert tokenConvert) {
        this.authUseCase = authUseCase;
        this.tokenConvert = tokenConvert;
    }

    @GetMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    TokenResource login(@RequestBody @Valid UserRequest userReq) {
        TokenEntity token = authUseCase.login(userReq);
        return tokenConvert.convertTokenEntityToResource(token);
    }
}
