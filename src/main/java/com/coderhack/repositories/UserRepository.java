package com.coderhack.repositories;

import com.coderhack.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByOrderByScoreDesc();
    Optional<User> findByUserName(String userName);
}
