package com.betrybe.agrix.agrix.controllers;

import com.betrybe.agrix.agrix.controllers.dto.AuthDto;
import com.betrybe.agrix.agrix.controllers.dto.TokenDto;
import com.betrybe.agrix.agrix.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling authentication requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Route post for create a new token.
   */
  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);

    String token = tokenService.generateToken(auth.getName());

    return new TokenDto(token);
  }
}
