package src.book.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.book.api.convert.tokenConvert;
import src.book.api.requests.userRequest;
import src.book.api.resources.tokenResource;
import src.book.core.entities.tokenEntity;
import src.book.core.usecases.authUseCase;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class authController extends baseController {
    private final authUseCase authUseCase;

    private final tokenConvert tokenConvert;

    @Autowired
    public authController(src.book.core.usecases.authUseCase authUseCase, src.book.api.convert.tokenConvert tokenConvert) {
        this.authUseCase = authUseCase;
        this.tokenConvert = tokenConvert;
    }

    @GetMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    tokenResource login(@RequestBody @Valid userRequest userReq) {
        tokenEntity token = authUseCase.login(userReq);
        return tokenConvert.convertTokenEntityToResource(token);
    }
}
