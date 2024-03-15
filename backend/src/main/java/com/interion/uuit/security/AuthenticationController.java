package com.interion.uuit.security;

import com.interion.uuit.config.TokenService;
import com.interion.uuit.dto.Login;
import com.interion.uuit.dto.RegisterRequest;
import com.interion.uuit.dto.Token;

import com.interion.uuit.enums.Role;
import com.interion.uuit.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody RegisterRequest request) {
        if (nonNull(this.repository.findByEmail(request.email()))) {
            return ResponseEntity.badRequest().build();
        }

        var encryptedPassword = encoder.encode(request.password());
        var newUser = com.interion.uuit.entities.User
                .builder()
                .email(request.email())
                .password(encryptedPassword)
                .role(Role.ADMIN)
                .build();

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Login login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new Token(token));
    }

}
