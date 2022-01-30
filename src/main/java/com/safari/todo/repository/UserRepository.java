package com.safari.todo.repository;

import com.safari.todo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameAndDeletedIsFalse(String username);
}
