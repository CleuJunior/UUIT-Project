package com.interion.uuit.mapper;

import com.interion.uuit.dto.StudentRequest;
import com.interion.uuit.dto.StudentSummaryJson;
import com.interion.uuit.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
public class StudentFactory {

    public Student from(StudentRequest json) {
        return Student.builder()
                .firstName(json.firstName())
                .lastName( json.lastName())
                .email(json.email())
                .registration(json.registration())
                .build();
    }

    public StudentRequest from(Student student) {
        return new StudentRequest(
                student.getId().toString(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getRegistration()
        );
    }

    public List<StudentRequest> from(List<Student> students) {
        return students
                .stream()
                .map(this::from)
                .toList();
    }

    public Page<StudentRequest> from(Page<Student> students) {
        return students.map(this::from);
    }


    public StudentSummaryJson studentSummary(Student student) {
        var fullName = format("%s %s", student.getFirstName(), student.getLastName());

        return new StudentSummaryJson(
                student.getId().toString(),
                fullName,
                student.getEmail(),
                student.getRegistration(),
                student.getUserId()
        );
    }

}
