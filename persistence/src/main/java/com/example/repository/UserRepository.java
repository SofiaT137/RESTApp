package com.example.repository;

import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByFirstNameAndLastName(String firstName, String lastName);

    Page<User> findByBirthday(LocalDateTime birthday, Pageable pageable);

    @Query(value = "select u from User u where u.address.id = :addressId")
    Page<User> findAllUserByFullAddress(Long addressId, Pageable pageable);
}
