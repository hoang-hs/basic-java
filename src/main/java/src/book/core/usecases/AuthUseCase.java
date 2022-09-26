package src.book.core.usecases;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import src.book.api.requests.LoginRequest;
import src.book.core.entities.TokenEntity;
import src.book.core.entities.UserEntity;
import src.book.core.ports.IUserRepositoryPort;
import src.book.exception.ResourceNotFoundException;
import src.book.exception.SystemErrorException;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class AuthUseCase {

    @NotNull
    private final AuthenticationManager authenticationManager;

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Value("${security.jwt.expire-length}")
    private int expireLength;

    private final IUserRepositoryPort userRepositoryPort;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Autowired
    public AuthUseCase(AuthenticationManager authenticationManager, IUserRepositoryPort userRepositoryPort) {
        this.authenticationManager = authenticationManager;
        this.userRepositoryPort = userRepositoryPort;
    }

    public TokenEntity login(LoginRequest req) {
        String username = req.getUsername();
        String password = req.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Optional<UserEntity> user = userRepositoryPort.getUserByUsername(req.getUsername());
            if (user.isEmpty()) {
                throw SystemErrorException.Default();
            }
            Claims claims = Jwts.claims().setSubject(username);
            claims.put("auth", user.get().getRole());

            Date now = new Date();
            Date validity = new Date(now.getTime() + expireLength);

            String atToken = Jwts.builder()//
                    .setClaims(claims)//
                    .setIssuedAt(now)//
                    .setExpiration(validity)//
                    .signWith(SignatureAlgorithm.HS256, secretKey)//
                    .compact();
            return new TokenEntity(atToken, expireLength, "a", 1);
        } catch (AuthenticationException e) {
            if (Objects.equals(e.getMessage(), "user not found")) {
                throw ResourceNotFoundException.WithMessage(e.getMessage());
            } else {
                throw ResourceNotFoundException.WithMessage("password wrong");
            }
        }
    }

}
