package com.interion.uuit.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("admin"),
    USER("user");

    private final String role;
}

