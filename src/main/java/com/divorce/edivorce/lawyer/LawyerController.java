package com.divorce.edivorce.lawyer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/lawyer")
@RestController
@RequiredArgsConstructor
public class LawyerController {

    private final LawyerService service;

    @PostMapping
    public ResponseEntity<DivorceApplicationResponse> initDivorce(@RequestBody DivorceRequest request, Authentication authentication) throws IllegalAccessException {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("LAWYER"))){
            throw new IllegalAccessException("User is not authorized");
        }
        return ResponseEntity.ok(this.service.initDivorce(request));
    }


}
