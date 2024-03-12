package com.interion.uuit.resources;

import com.interion.uuit.dto.Login;
import com.interion.uuit.dto.Token;
import com.interion.uuit.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Login request) {
        var response = authenticationService.loginService(request);
        return ResponseEntity.ok(response);
    }


}
