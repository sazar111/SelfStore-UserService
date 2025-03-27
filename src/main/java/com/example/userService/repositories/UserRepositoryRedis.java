package com.example.userService.repositories;

import com.example.userService.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepositoryRedis extends CrudRepository<User, UUID> {

}