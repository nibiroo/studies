package io.github.nibiroo.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.nibiroo.domain.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.key}")
    private String key;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(key);
            long expString = Long.parseLong(expiration);
            LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expString);
            Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(instant);

            String token = JWT
                            .create()
                            .withIssuer("auth-api")
                            .withSubject(user.getLogin())
                            .withExpiresAt(date)
                            .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT
                    .require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }
}
