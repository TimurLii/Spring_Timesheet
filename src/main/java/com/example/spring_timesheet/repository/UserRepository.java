package com.example.spring_timesheet.repository;

import com.example.spring_timesheet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findByLogin(String login);
}
