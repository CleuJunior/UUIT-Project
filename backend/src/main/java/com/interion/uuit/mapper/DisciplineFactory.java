package com.interion.uuit.mapper;

import com.interion.uuit.dto.DisciplineJson;
import com.interion.uuit.dto.StudentSummaryJson;
import com.interion.uuit.entities.Discipline;
import com.interion.uuit.entities.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DisciplineFactory {

    public Discipline from(DisciplineJson request) {
        return new Discipline(
                request.name(),
                request.start(),
                request.end(),
                request.capacity(),
                request.total(),
                request.open()
        );
    }

    public DisciplineJson from(Discipline discipline) {
        return new DisciplineJson(
                discipline.getId().toString(),
                discipline.getName(),
                discipline.getStart(),
                discipline.getEnd(),
                discipline.getCapacity(),
                discipline.getTotal(),
                discipline.isOpen(),
                discipline.getStudents()
        );
    }

    private StudentSummaryJson studentToStudentSummaryJsonResponse(Student student) {
        var fullName = String.format("%s %s", student.getFirstName(), student.getLastName());

        return new StudentSummaryJson(
                student.getId().toString(),
                fullName,
                student.getEmail(),
                student.getRegistration());
    }

    public List<DisciplineJson> from(List<Discipline> disciplines) {
        return disciplines
                .stream()
                .map(this::from)
                .toList();
    }

}
