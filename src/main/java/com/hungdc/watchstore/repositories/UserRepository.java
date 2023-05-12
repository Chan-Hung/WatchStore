package com.hungdc.watchstore.repositories;

import com.hungdc.watchstore.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{'email': ?0}")
    Optional<User> getTaiKhoan(String email);

    @Query(value = "{'Email': ?0}", exists = true)
    boolean validateEmail(String Email);
}
