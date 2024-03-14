package com.interion.uuit.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role2 implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
