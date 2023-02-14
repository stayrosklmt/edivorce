package com.divorce.edivorce.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @CrossOrigin(origins = "*")
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/reset-password")
  public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordRequest request)
  {
    return ResponseEntity.ok(this.service.resetPassword(request));
  }
}
