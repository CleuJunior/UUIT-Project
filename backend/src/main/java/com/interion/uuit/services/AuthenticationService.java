package com.interion.uuit.services;

import com.interion.uuit.dto.Login;
import com.interion.uuit.dto.Token;
import com.interion.uuit.entities.BaseEntityDetails;
import com.interion.uuit.repositories.UserRepository;
import com.interion.uuit.security.TokenSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenSecurityService service;
    private final UserRepository repository;

    public Token loginService(Login request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var userDetails = (UserDetails) auth.getPrincipal();

        var user = (BaseEntityDetails) this.repository
                .findByEmail(userDetails.getUsername())
                .orElseThrow();

        return new Token(this.service.generateToken(user));
    }
}