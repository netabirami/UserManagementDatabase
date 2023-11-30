package com.example.User_Management_Database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByUserNameStartingWith(String letter);
    // "select * from users where name like "a%""
}