package com.interion.uuit.repositories;

import com.interion.uuit.entities.Professor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends MongoRepository<Professor, ObjectId> {

    @Override
    Optional<Professor> findById(ObjectId id);
}