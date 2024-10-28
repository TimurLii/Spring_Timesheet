package com.example.spring_timesheet.repository;

import com.example.spring_timesheet.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
