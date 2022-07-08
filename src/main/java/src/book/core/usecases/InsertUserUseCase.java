package src.book.core.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import src.book.api.requests.UserRequest;
import src.book.core.entities.UserEntity;
import src.book.core.ports.IUserRepositoryPort;
import src.book.exception.AppException;

import java.util.Optional;


@Service
public class InsertUserUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetUserUseCase.class);
    private final IUserRepositoryPort userRepositoryPort;
    PasswordEncoder encoder;

    @Autowired
    public InsertUserUseCase(IUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public UserEntity insertUser(UserRequest userReq) {
        Optional<UserEntity> user = userRepositoryPort.getUserByUsername(userReq.getUsername());
        if (user.isPresent()) {
            throw new AppException("username have been exist", HttpStatus.NOT_IMPLEMENTED);
        }
        String password = encoder.encode(userReq.getPassword());
        UserEntity userEntity = new UserEntity(userReq.getUsername(), password, userReq.getRole());
        return userRepositoryPort.insertUser(userEntity);
    }

}
