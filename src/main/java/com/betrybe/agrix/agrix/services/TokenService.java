package com.betrybe.agrix.agrix.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling token business logic.
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Generates a new token for a given username.
   */
  public String generateToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(generateExpiration())
        .sign(algorithm);
  }

  /**
   * Generates a new expiration date for a token.
   */
  private Instant generateExpiration() {
    return Instant.now()
        .plus(2, ChronoUnit.HOURS);
  }

  /**
   * Validates a given token and returns the username.
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();
  }
}
