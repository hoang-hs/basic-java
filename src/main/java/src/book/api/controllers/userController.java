package src.book.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.book.core.entities.response;
import src.book.core.entities.user;
import src.book.api.mappers.userMapper;
import src.book.core.usecases.getUserUseCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class userController extends baseController {

    private final getUserUseCase getUserUseCase;

    @Autowired
    public userController(getUserUseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }

    @GetMapping("")
    ResponseEntity<Object> getAllUser() {
        response res = getUserUseCase.getAll();
        if (res.isError()) {
            return responseError(res);
        }
       // @SuppressWarnings("unchecked")
        List<user> users = null;
        if(res.getData() instanceof List<?>) {
             users = (List<user>) res.getData();
        }
        //List<user> users = (List<user>) res.getData();
        return responseData(userMapper.NewListUsersResource(users));
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getUserById(@PathVariable Long id) {
        response res = getUserUseCase.getUserById(id);
        if (res.isError()) {
            return responseError(res);
        }
        user user = (user) res.getData();
        return responseData(userMapper.NewUserResource(user));
    }

}
