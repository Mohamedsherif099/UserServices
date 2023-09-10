package com.jobhacker.userservices.controller;

import com.jobhacker.userservices.dto.AuthRequestDto;
import com.jobhacker.userservices.entity.User;
import com.jobhacker.userservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticateController {

    @Autowired
    private UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticateController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/authenticate")
    public List<User> authenticate(@RequestBody AuthRequestDto authRequestDto){
        String email = authRequestDto.getEmail();
        String password = authRequestDto.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        if (authentication.isAuthenticated()){
            return userRepository.findByEmail(email);
        }
        return null;
    }
    @GetMapping("/welcome")
    public String sayWelcome(){
        return "Welcome to Rest Controller";
    }
}
