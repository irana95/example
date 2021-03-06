package com.example.sp.dao;

import com.example.sp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
    List<User> findAllUserByUsernameAndPassword(String username, String password);

}
