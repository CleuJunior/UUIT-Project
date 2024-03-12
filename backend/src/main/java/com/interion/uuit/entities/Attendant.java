package com.interion.uuit.entities;

import com.interion.uuit.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document("Attendant")
@Getter @Setter
public class Attendant extends BaseEntityDetails {

    @Field("firt_name")
    private String firtName;
    @Field("last_name")
    private String lastName;

    public Attendant() {
    }

    public Attendant(String firtName, String lastName, String email, String registration, String password) {
        super(email, registration, password);
        this.firtName = firtName;
        this.lastName = lastName;
        super.roles.add(Role.ADMIN);
        super.roles.add(Role.USER);
    }

    public Attendant(String firtName, String lastName, ObjectId id, String email, String registration, String password) {
        super(id, email, registration, password);
        this.firtName = firtName;
        this.lastName = lastName;
        super.roles.add(Role.ADMIN);
        super.roles.add(Role.USER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Attendant attendant = (Attendant) o;
        return Objects.equals(firtName, attendant.firtName) && Objects.equals(lastName, attendant.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firtName, lastName);
    }
}
