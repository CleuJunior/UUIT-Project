package com.interion.uuit.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Discipline")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Discipline extends BaseMongoEntity {

    private String name;
    private String start;
    private String end;
    private int capacity;
    private int total;
    private boolean open;
    private List<Student> students;

    public Discipline(String name, String start, String end, int capacity, int total, boolean open) {
        super();
        this.name = name;
        this.start = start;
        this.end = end;
        this.capacity = capacity;
        this.total = total;
        this.open = open;
    }

}
