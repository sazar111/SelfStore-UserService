package com.example.userService.repositories;

import com.example.userService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
     Optional<User> findById(UUID id);
     Optional<User> findByEmail(String email);
     User save(User user);
}
