package com.interion.uuit.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Set;

public record DisciplineJson(
        String id,
        @NotNull(message = "{bean.validation.field.null}")
        @NotBlank(message = "{bean.validation.field.blank}")
        String name,
        @NotNull(message = "{bean.validation.field.null}")
        @NotBlank(message = "{bean.validation.field.blank}")
        String start,
        @NotNull(message = "{bean.validation.field.null}")
        @NotBlank(message = "{bean.validation.field.blank}")
        String end,
        @PositiveOrZero(message = "{bean.validation.field.discipline.value.positive.capacity}")
        @Max(value = 50, message = "{bean.validation.field.discipline.value.max}")
        int capacity,
        @PositiveOrZero(message = "{bean.validation.field.discipline.value.positive.capacity.total}")
        int total,
        boolean open,
        Set<StudentSummaryJson> students

) {
}
