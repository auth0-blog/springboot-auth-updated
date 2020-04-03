package com.sample.springbootauth.repository;

import com.sample.springbootauth.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
  // This method will be used by the authentication feature.
  ApplicationUser findByUsername(String username);
}
