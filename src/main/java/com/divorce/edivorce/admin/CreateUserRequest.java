package com.divorce.edivorce.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String socialSecurityNumber;
    private String taxIdentificationNumber;
    private String role;
    private String password;
}
