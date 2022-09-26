package src.book.security;

import org.springframework.beans.factory.annotation.Value;

public class JwtTokenProvider {

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Value("${security.jwt.expire-length}")
    private int expireLength;


}
