package com.interion.uuit.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("Student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student extends BaseMongoEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String registration;

    private Student(ObjectId id, String firstName, String lastName, String email, String registration) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registration = registration;
    }

    public static Student of(ObjectId id, String name, String lastName, String email, String registration)  {
        return new Student(id, name, lastName, email, registration);
    }

    public static Student of(String name, String lastName, String email, String registration)  {
        return new Student(name, lastName, email, registration);
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
