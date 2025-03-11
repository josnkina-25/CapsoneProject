package com.josias.empoweridd.repository;

import com.josias.empoweridd.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
   AdminUser findByUsername(String username);
    // check if the username and email already exist
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
