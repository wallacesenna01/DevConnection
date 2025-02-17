package com.br.wallaceartur.DevConnection.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private final String secret = "segredo";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("auth")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("ERROR WHILE GENERATING TOKEN", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodeJWT = JWT.require(algorithm)
                    .withIssuer("auth")
                    .build()
                    .verify(token);
            return decodeJWT.getSubject();

        } catch (Exception exception) {
            return "";
        }

    }

    public Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000);
    }
}
