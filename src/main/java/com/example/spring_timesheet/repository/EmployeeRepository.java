package com.example.spring_timesheet.repository;

import com.example.spring_timesheet.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
