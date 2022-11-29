package src.book.present.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import src.book.core.usecases.GetUserUseCase;
import src.book.present.requests.LoginRequest;
import src.book.core.entities.TokenEntity;
import src.book.core.entities.UserEntity;
import src.book.core.ports.IUserRepositoryPort;
import src.book.core.exception.ResourceNotFoundException;
import src.book.core.exception.SystemErrorException;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
//@RequiredArgsConstructor
public class AuthUseCase {

    @NotNull
    private final AuthenticationManager authenticationManager;

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Value("${security.jwt.expire-length}")
    private int expireLength;

    private final GetUserUseCase getUserUseCase;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Autowired
    public AuthUseCase(AuthenticationManager authenticationManager, GetUserUseCase getUserUseCase) {
        this.authenticationManager = authenticationManager;
        this.getUserUseCase = getUserUseCase;
    }

    public TokenEntity login(LoginRequest req) {
        String username = req.getUsername();
        String password = req.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserEntity user = getUserUseCase.getUserByUserName(req.getUsername());
            Claims claims = Jwts.claims().setSubject(username);
            claims.put("auth", user.getRole());

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
            if (Objects.equals(e.getMessage(), ResourceNotFoundException.Default().getMessage())) {
                throw ResourceNotFoundException.WithMessage(e.getMessage());
            } else {
                throw ResourceNotFoundException.WithMessage("password wrong");
            }
        }
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
        return authorities;
    }
}
