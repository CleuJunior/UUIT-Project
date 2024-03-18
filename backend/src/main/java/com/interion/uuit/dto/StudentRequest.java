package com.interion.uuit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"id"})
public record StudentRequest(
        String id,
        @NotNull(message = "{bean.validation.field.null}")
        @NotBlank(message = "{bean.validation.field.blank}")
        @JsonProperty("first_name")
        String firstName,
        @NotNull(message = "{bean.validation.field.null}")
        @NotBlank(message = "{bean.validation.field.blank}")
        @JsonProperty("last_name")
        String lastName,
        @Email
        String email,
        String password,
        @NotNull(message = "{bean.validation.field.null}")
        @NotBlank(message = "{bean.validation.field.blank}")
        String registration
) {
}
