package src.book.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.book.api.requests.userRequest;
import src.book.core.entities.tokenEntity;
import src.book.core.usecases.authUseCase;

@RestController
@RequestMapping("/auth")
@Validated
public class authController extends baseController {
    private final authUseCase authUseCase;

    @Autowired
    public authController(src.book.core.usecases.authUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }
    @GetMapping("/login")
    ResponseEntity<Object> login(@RequestBody userRequest userReq){
        tokenEntity token = authUseCase.login(userReq);
        return responseData(token);
    }
}
