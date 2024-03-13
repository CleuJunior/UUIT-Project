package com.interion.uuit.security;

import com.interion.uuit.config.JwtService;
import com.interion.uuit.dto.Login;
import com.interion.uuit.dto.RegisterRequest;
import com.interion.uuit.dto.Token;
import com.interion.uuit.entities.User;
import com.interion.uuit.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.interion.uuit.enums.Role.USER;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Token register(RegisterRequest request) {
        var user = User
                .builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .role(USER)
                .build();

        repository.save(user);
        var token = jwtService.generateToken(user);

        return new Token(token);
    }

    public Token authenticate(Login login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.email(),
                        login.password()
                )
        );

        var user = repository.findByEmail(login.email()).orElseThrow();
        var token = jwtService.generateToken(user);

        return new Token(token);
    }
}