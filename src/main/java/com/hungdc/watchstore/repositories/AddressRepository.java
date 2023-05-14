package com.hungdc.watchstore.repositories;

import com.hungdc.watchstore.entities.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AddressRepository extends MongoRepository<Address, String> {
    @Query(value = "{'email': ?0}")
    Optional<Address> getByEmail(String email);
}
