package com.divorce.edivorce.admin;

import com.divorce.edivorce.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers(Authentication authentication) throws IllegalAccessException {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            throw new IllegalAccessException("User is not authorized");
        }
        return ResponseEntity.ok(this.service.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id, Authentication authentication) throws IllegalAccessException {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            throw new IllegalAccessException("User is not authorized");
        }
        return ResponseEntity.ok(this.service.getUser(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody CreateUserRequest request, Authentication authentication) throws IllegalAccessException {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            throw new IllegalAccessException("User is not authorized");
        }
        return ResponseEntity.ok(this.service.addUser(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id, Authentication authentication) throws IllegalAccessException {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            throw new IllegalAccessException("User is not authorized");
        }
        this.service.deleteUser(id);
        return ResponseEntity.ok("User with: " + id + " deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequest request, Authentication authentication) throws IllegalAccessException {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            throw new IllegalAccessException("User is not authorized");
        }
        return ResponseEntity.ok(this.service.updateUser(id, request));
    }
}
