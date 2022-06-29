package src.book.core.usecases;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import src.book.api.requests.userRequest;
import src.book.core.entities.tokenEntity;
import src.book.core.entities.userEntity;
import src.book.core.ports.iUserRepositoryPort;
import src.book.exception.unauthorizedException;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class authUseCase {
    private static final Logger log = LoggerFactory.getLogger(getUserUseCase.class);

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Value("${security.jwt.expire-length}")
    private int expireLength;

    PasswordEncoder encoder;

    private final iUserRepositoryPort userRepositoryPort;

    @Autowired
    public authUseCase(iUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    public tokenEntity login(userRequest userReq) {
        Optional<userEntity> user = userRepositoryPort.getUserByUsername(userReq.getUsername());
        if (user.isEmpty()) {
            throw unauthorizedException.WithMessage("username incorrect");
        }
        if (!encoder.matches(userReq.getPassword(), user.get().getPassword())) {
            throw unauthorizedException.WithMessage("password incorrect");
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
        return new tokenEntity(at, expireLength, "a", 1);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
