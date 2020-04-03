package com.sample.springbootauth.repository;

import com.sample.springbootauth.user.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<ApplicationUser, String> {
  // This method will be used by the authentication feature.
  @Query("{username : ?0}")
  ApplicationUser findByUsername(String username);
}
