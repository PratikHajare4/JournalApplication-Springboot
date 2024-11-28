package com.pratik.JournalApp.repository;

import com.pratik.JournalApp.entity.JournalEntry;
import com.pratik.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);

    void deleteByUserName(String userName);
}
