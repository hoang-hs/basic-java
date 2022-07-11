package src.book.core.usecases;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import src.book.api.requests.LoginRequest;
import src.book.core.entities.TokenEntity;
import src.book.core.entities.UserEntity;
import src.book.core.ports.IUserRepositoryPort;
import src.book.exception.UnauthorizedException;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthUseCase {

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Value("${security.jwt.expire-length}")
    private int expireLength;

    PasswordEncoder encoder;

    private final IUserRepositoryPort userRepositoryPort;

    @Autowired
    public AuthUseCase(IUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    public TokenEntity login(LoginRequest req) {
        Optional<UserEntity> user = userRepositoryPort.getUserByUsername(req.getUsername());
        if (user.isEmpty()) {
            throw UnauthorizedException.WithMessage("username incorrect");
        }
        if (!encoder.matches(req.getPassword(), user.get().getPassword())) {
            throw UnauthorizedException.WithMessage("password incorrect");
        }
        Date now = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(now);
        cl.add(Calendar.MINUTE, expireLength);

        String at = Jwts.builder()
                .setSubject(Long.toString(user.get().getId()))
                .setIssuedAt(now)
                .setExpiration(cl.getTime())
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
        return new TokenEntity(at, expireLength, "a", 1);
    }
}
