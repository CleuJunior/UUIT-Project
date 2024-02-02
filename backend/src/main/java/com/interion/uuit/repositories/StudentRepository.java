package com.interion.uuit.repositories;

import com.interion.uuit.entities.Discipline;
import com.interion.uuit.entities.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {

    @Override
    Optional<Student> findById(ObjectId id);
}