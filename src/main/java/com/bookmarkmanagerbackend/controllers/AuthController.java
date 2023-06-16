package com.bookmarkmanagerbackend.controllers;


import com.bookmarkmanagerbackend.models.Token;
import com.bookmarkmanagerbackend.models.User;
import com.bookmarkmanagerbackend.services.TokenService;
import com.bookmarkmanagerbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    public AuthController(UserService userService, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public Token authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        String jwt = tokenService.getToken(currentUser);
        return new Token(jwt);
    }

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }
}