package com.example.userService.repositories;

import com.example.userService.models.Token;
import com.example.userService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface TokenRepository extends JpaRepository<Token, UUID> {
  Optional<Token> findByValueAndDeleted(String value, boolean deleted);
  //Optional<Token> findByUserEmail(String email);
  Optional<Token> findByValue(String value);
  Optional<Token> findByUserAndDeleted(User user, boolean deleted);
  Optional<Token> findByUserAndDeletedAndExpiryDateAfter(User user, boolean deleted, Date expiryDate );
  @Override
  <S extends Token> S save(S entity);
}