package com.example.spring_timesheet.repository;

import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.model.Timesheet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProjectRepository {
    //region Field
    private static Long sequence = 1l;
    private final List<Project> projects = new ArrayList<>();
    //endregion

    //region Method getById
    public Project getById(Long id) {
        Project project = projects.stream()
                .filter(item -> Objects.equals(item.getId(), id))
                .findFirst().get();
        return project;
    }
    //endregion

    //region Method getAll
    public List<Project> getAll() {
        return List.copyOf(projects);
    }
    //endregion

    //region Method create
    public Project create(Project project) {
        project.setId(sequence++);
        projects.add(project);
        return project;
    }

    //endregion

    //region Method delete
    public ResponseEntity<Void> delete(Long id) {
        projects.stream()
                .filter(item -> Objects.equals(item.getId(), id))
                .findFirst()
                .ifPresent(projects::remove);
        return ResponseEntity.noContent().build();
    }

    public Project find(Long projectId) {
        return projects.stream()
                .filter(item -> Objects.equals(item.getId(), projectId))
                .findFirst().get();
    }

    public Project getByID(Long projectId) {
        Project project = projects.stream()
                .filter(it -> Objects.equals((Long)it.getId(), projectId))
                .findFirst()
                .get();

        return project ;
    }
    //endregion


}
