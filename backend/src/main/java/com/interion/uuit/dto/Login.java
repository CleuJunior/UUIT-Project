package com.interion.uuit.dto;

import jakarta.validation.constraints.Email;

public record Login(String email, String password) {
}
