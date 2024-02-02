package com.interion.uuit.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("Student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student extends BaseMongoEntity{

    private String name;
    private String lastName;
    private String email;
    private String registration;
    private List<Discipline> disciplines;

    public Student(String name, String lastName, String email, String registration) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
