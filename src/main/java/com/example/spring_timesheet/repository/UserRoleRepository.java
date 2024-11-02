package com.example.spring_timesheet.repository;

import com.example.spring_timesheet.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
//    @Query(nativeQuery = true, value = "select role_name from users_roles ur where ur.user_id = :userId")
    List<UserRole> findByUserId(Long userId);

}
