package com.spring.authentication.springsecurityauthentication.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {
    //Returning hardcoded user due to no db support in this project for now.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("test", "test", new ArrayList<>());
    }
}
