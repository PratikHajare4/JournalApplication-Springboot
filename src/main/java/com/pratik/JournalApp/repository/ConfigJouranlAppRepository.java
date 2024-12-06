package com.pratik.JournalApp.repository;

import com.pratik.JournalApp.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJouranlAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {


}
