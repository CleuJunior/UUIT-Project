package com.interion.uuit.entities;

import com.interion.uuit.dto.StudentSummaryJson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document("Discipline")
@NoArgsConstructor
@Getter
@Setter
public class Discipline extends BaseMongoEntity {

    private String name;
    private String start;
    private String end;
    private int capacity;
    private int total;
    private boolean open;
    private Set<StudentSummaryJson> students = new HashSet<>();

    public Discipline(ObjectId id, String name, String start, String end, int capacity, int total, boolean open) {
        super(id);
        this.name = name;
        this.start = start;
        this.end = end;
        this.capacity = capacity;
        this.total = total;
        this.open = open;
    }

    public Discipline(String name, String start, String end, int capacity, int total, boolean open) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.capacity = capacity;
        this.total = total;
        this.open = open;
    }

    public void addStudent(StudentSummaryJson student) {
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Discipline that = (Discipline) o;

        if (capacity != that.capacity) return false;
        if (total != that.total) return false;
        if (open != that.open) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(start, that.start)) return false;
        return Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + total;
        result = 31 * result + (open ? 1 : 0);
        return result;
    }
}
