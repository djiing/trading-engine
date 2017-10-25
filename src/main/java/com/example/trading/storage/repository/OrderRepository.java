package com.example.trading.storage.repository;

import com.example.trading.model.UnsatisfiedOrders;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<UnsatisfiedOrders, String> {
}
