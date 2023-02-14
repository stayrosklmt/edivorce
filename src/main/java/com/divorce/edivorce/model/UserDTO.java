package com.divorce.edivorce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String socialSecurityNumber;
    private String taxIdentificationNumber;
}
