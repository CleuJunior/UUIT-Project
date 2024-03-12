package com.interion.uuit.repositories;

import com.interion.uuit.entities.Attendant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendantRepository extends MongoRepository<Attendant, ObjectId> {
}
