package com.divorce.edivorce.husband;

import com.divorce.edivorce.notary.DivorceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/husband")
@RequiredArgsConstructor
public class HusbandController {

    private final HusbandService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<DivorceResponse>> getDivorce(@PathVariable UUID id, Authentication authentication) throws IllegalAccessException {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("USER"))){
            throw new IllegalAccessException("User is not authorized");
        }
        return ResponseEntity.ok(this.service.getDivorce(id));
    }

    @PutMapping
    public ResponseEntity<DivorceResponse> signDivorce(@RequestBody AcceptDivorce divorce, Authentication authentication) throws Exception {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("USER"))){
            throw new IllegalAccessException("User is not authorized");
        }
        return ResponseEntity.ok(this.service.signHusband(divorce));
    }

}
