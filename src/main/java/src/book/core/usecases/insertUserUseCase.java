package src.book.core.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import src.book.api.requests.userRequest;
import src.book.core.entities.userEntity;
import src.book.core.ports.iUserRepositoryPort;
import src.book.exception.appException;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Optional;


@Service
public class insertUserUseCase {
    private static final Logger logger = LoggerFactory.getLogger(getUserUseCase.class);
    private final iUserRepositoryPort userRepositoryPort;
    PasswordEncoder encoder;

    @Autowired
    public insertUserUseCase(iUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public userEntity insertUser(userRequest userReq) {
        Optional<userEntity> user = userRepositoryPort.getUserByUsername(userReq.getUsername());
        if (user.isPresent()) {
            throw new appException("username have been exist", HttpStatus.NOT_IMPLEMENTED);
        }
//        String password = encoder.encode(userReq.getPassword());
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = encoder.encode(userReq.getPassword());
        userEntity userEntity = new userEntity(userReq.getUsername(), password);
        return userRepositoryPort.insertUser(userEntity);
    }

}
