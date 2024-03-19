package com.interion.uuit.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Document("Student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Student extends BaseMongoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;
    private String firstName;
    private String lastName;
    private String email;
    private String registration;
    public Student(ObjectId id, String firstName, String lastName, String email, String registration) {
        super(id);
        this.firstName = firstName;
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
