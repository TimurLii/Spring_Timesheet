package com.example.spring_timesheet.model.serevice;

import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    //region Field
    private final ProjectRepository projectRepository;
    //endregion

    //region Constructor
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    //endregion

    //region Method CRUD
    public List<Project> getAll() {
        return projectRepository.getAll();
    }
    public Project getById(Long id) {
        return projectRepository.getById(id);
    }
    public Project create(Project project) {
        return projectRepository.create(project);
    }
    public void delete(Long id) {
        projectRepository.delete(id);
    }

    public Optional<Project> findById(Long projectId) {
        return Optional.ofNullable(projectRepository.getByID(projectId));

    }
    //endregion C

}
