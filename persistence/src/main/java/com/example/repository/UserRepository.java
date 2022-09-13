package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The "UserRepository" interface extends JpaRepository functionality for the User entity
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Method "findUserByLogin" searches the "User" entity by its login
     * @param login String login
     * @return Optional of the "User" entity
     */
    Optional<User> findUserByLogin(String login);
}
