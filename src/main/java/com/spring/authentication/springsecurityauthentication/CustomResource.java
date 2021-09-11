package com.spring.authentication.springsecurityauthentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomResource {

    @GetMapping("/authenticate")
    public String authenticateUser() {
        return "Hello User";
    }

}
