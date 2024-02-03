package com.interion.uuit.repositories;

import com.interion.uuit.entities.Discipline;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisciplineRepository extends MongoRepository<Discipline, ObjectId> {

    @Override
    Optional<Discipline> findById(ObjectId id);
}