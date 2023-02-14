package com.divorce.edivorce.auth;

import com.divorce.edivorce.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private String token;
  private UUID id;
  private String username;
  private Role role;
}
