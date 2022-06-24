package src.book.core.usecases;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
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
    private static final Logger logger = LoggerFactory.getLogger(getUserUseCase.class);

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
}
