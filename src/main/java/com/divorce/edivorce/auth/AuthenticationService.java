package com.divorce.edivorce.auth;

import com.divorce.edivorce.config.JwtService;
import com.divorce.edivorce.model.User;
import com.divorce.edivorce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    var user = repository.findByUsername(request.getUsername())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .id(user.getId())
            .username(user.getUsername())
            .role(user.getRole())
            .build();
  }

    public boolean resetPassword(ResetPasswordRequest request) {
      User user = this.repository.findByUsername(request.getUsername()).orElseThrow();
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getUsername(),
                      request.getCurrentPassword()
              )
      );
      if(request.getCurrentPassword().equals(request.getNewPassword())) {
        throw new BadCredentialsException("New and current password must not be the same");
      }
      user.setPassword(passwordEncoder.encode(request.getNewPassword()));
      this.repository.save(user);
      return true;
    }
}
