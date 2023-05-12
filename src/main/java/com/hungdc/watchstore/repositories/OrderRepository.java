package com.hungdc.watchstore.repositories;

import com.hungdc.watchstore.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
