package com.interion.uuit.dto;

import java.util.Set;

public record DisciplineJsonResponse(
        String id,
        String name,
        String start,
        String end,
        int capacity,
        int total,
        boolean open,
        Set<StudentSummaryJson> students

) {
}
