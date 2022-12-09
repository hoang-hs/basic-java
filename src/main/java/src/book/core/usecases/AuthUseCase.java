package src.book.core.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import src.book.core.entities.TokenEntity;
import src.book.core.entities.UserEntity;
import src.book.core.exception.AppException;
import src.book.core.ports.IUserRepositoryPort;
import src.book.present.requests.LoginRequest;
import src.book.present.requests.UserRequest;
import src.book.present.security.JwtTokenProvider;

import java.util.Optional;

@Service
public class AuthUseCase {
    private final IUserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Value("${security.jwt.expire-length}")
    private int expireAccessToken;

    @Autowired
    public AuthUseCase(IUserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public TokenEntity signin(LoginRequest req) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (AuthenticationException ex) {
            throw new AppException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Optional<UserEntity> user = userRepositoryPort.getUserByUsername(req.getUsername());
        if (user.isEmpty()) {
            throw new AppException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        String aToken = jwtTokenProvider.createToken(user.get().getUsername(), user.get().getRole());

        return new TokenEntity(aToken, expireAccessToken, "a", 1);
    }

    public UserEntity signup(UserRequest userReq) {
        Optional<UserEntity> user = userRepositoryPort.getUserByUsername(userReq.getUsername());
        if (user.isPresent()) {
            throw new AppException("username have been exist", HttpStatus.NOT_IMPLEMENTED);
        }
        String password = passwordEncoder.encode(userReq.getPassword());
        UserEntity userEntity = new UserEntity(userReq.getUsername(), password, userReq.getRole());
        return userRepositoryPort.insertUser(userEntity);

    }

//    public void delete(String username) {
//        userRepository.deleteByUsername(username);
//    }

//    public UserEntity search(String username) {
//        Optional<UserEntity> user = userRepositoryPort.getUserByUsername(username);
//        if (user.isEmpty()) {
//            throw new AppException("The user doesn't exist", HttpStatus.NOT_FOUND);
//        }
//        return user.get();
//    }

//    public String refresh(String username) {
//        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
//    }

}
