package com.interion.uuit.mapper;

import com.interion.uuit.dto.StudentJson;
import com.interion.uuit.dto.StudentSummaryJson;
import com.interion.uuit.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentFactory {

    public Student from(StudentJson json) {
        return Student.of(json.firstName(), json.lastName(), json.email(),json.registration());
    }

    public StudentJson from(Student student) {
        return new StudentJson(
                student.getId().toString(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getRegistration()
        );
    }

    public List<StudentJson> from(List<Student> students) {
        return students
                .stream()
                .map(this::from)
                .toList();
    }

    public Page<StudentJson> from(Page<Student> students) {
        return students.map(this::from);
    }


    public StudentSummaryJson studentSummary(Student student) {
        var fullName = String.format("%s %s", student.getFirstName(), student.getLastName());

        return new StudentSummaryJson(
                student.getId().toString(),
                fullName,
                student.getEmail(),
                student.getRegistration()
        );
    }

}
