package com.interion.uuit.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("admin"),
    STUDENT("student"),
    USER("user");

    private final String name;
}

