package com.divorce.edivorce.notary;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notary")
@RequiredArgsConstructor
public class NotaryController {

    private final NotaryService service;
    @GetMapping
    public ResponseEntity<List<DivorceResponse>> getDivorces(Authentication authentication) throws IllegalAccessException {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("NOTARY"))){
            throw new IllegalAccessException("User is not authorized");
        }
        return ResponseEntity.ok(this.service.getDivorces());
    }

    @PutMapping
    public ResponseEntity<DivorceResponse> approvalDivorce(@RequestBody ApprovalRequest request, Authentication authentication) throws IllegalAccessException {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("NOTARY"))){
            throw new IllegalAccessException("User is not authorized");
        }
        return ResponseEntity.ok(this.service.approvalDivorce(request));
    }
}
