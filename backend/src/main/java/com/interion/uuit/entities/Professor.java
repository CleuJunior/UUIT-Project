package com.interion.uuit.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document("Professor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Professor extends BaseMongoEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String registration;
    private Set<Discipline> disciplines = new HashSet<>();

    public Professor(String firstName, String lastName, String email, String registration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registration = registration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Professor professor = (Professor) o;
        return id.equals(professor.id) &&
                firstName.equals(professor.firstName) &&
                lastName.equals(professor.lastName) &&
                registration.equals(professor.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, registration);
    }

}
