package com.hungdc.watchstore.repositories;

import com.hungdc.watchstore.entities.Watch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WatchRepository extends MongoRepository<Watch, String> {
    @Query(value = "{'maDichVu': ?0}", exists = true)
    boolean validateWatchCode(String code);
}
