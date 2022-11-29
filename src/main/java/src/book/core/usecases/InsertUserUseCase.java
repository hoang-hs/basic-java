package src.book.core.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import src.book.core.entities.UserEntity;
import src.book.core.exception.AppException;
import src.book.core.ports.IUserRepositoryPort;
import src.book.present.requests.UserRequest;

import java.util.Optional;


@Service
public class InsertUserUseCase {
    private final IUserRepositoryPort userRepositoryPort;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InsertUserUseCase(IUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = new BCryptPasswordEncoder(12);
    }

    public UserEntity insertUser(UserRequest userReq) {
        Optional<UserEntity> user = userRepositoryPort.getUserByUsername(userReq.getUsername());
        if (user.isPresent()) {
            throw new AppException("username have been exist", HttpStatus.NOT_IMPLEMENTED);
        }
        String password = passwordEncoder.encode(userReq.getPassword());
        UserEntity userEntity = new UserEntity(userReq.getUsername(), password, userReq.getRole());
        return userRepositoryPort.insertUser(userEntity);
    }

}
