package com.spring.authentication.springsecurityauthentication.controllers;

import com.spring.authentication.springsecurityauthentication.models.AuthenticationRequest;
import com.spring.authentication.springsecurityauthentication.models.AuthenticationResponse;
import com.spring.authentication.springsecurityauthentication.services.CustomUserDetailService;
import com.spring.authentication.springsecurityauthentication.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @GetMapping("/login")
    public String authenticateUser() {
        return "Hello User";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest
                    .getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Invalid username or password", badCredentialsException);
        }
        final UserDetails userDetails = customUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwtToken = JwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
