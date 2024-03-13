package com.interion.uuit.security;

import com.interion.uuit.dto.Login;
import com.interion.uuit.dto.RegisterRequest;
import com.interion.uuit.dto.Token;
import com.interion.uuit.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Login login) {
        return ResponseEntity.ok(authenticationService.authenticate(login));
    }

}
