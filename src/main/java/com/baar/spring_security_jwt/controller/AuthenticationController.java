package com.baar.spring_security_jwt.controller;

import com.baar.spring_security_jwt.configuration.CustomUserDetailsService;
import com.baar.spring_security_jwt.jwt.JwtUtil;
import com.baar.spring_security_jwt.requests.AuthenticationRequest;
import com.baar.spring_security_jwt.responses.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    // Create Auth Token
    @PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Incorrect username or password", e);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));


    }
    @GetMapping("/secure")
    public String
    secureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[36];
        secureRandom.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder();
        String encodeToString = encoder.encodeToString(bytes);
        return encodeToString;
    }
}
