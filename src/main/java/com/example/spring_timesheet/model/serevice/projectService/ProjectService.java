package com.example.spring_timesheet.model.serevice.projectService;

import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.model.Timesheet;
import com.example.spring_timesheet.repository.ProjectRepository;
import com.example.spring_timesheet.repository.TimesheetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;

    public ProjectService(ProjectRepository projectRepository, TimesheetRepository timesheetRepository) {
        this.projectRepository = projectRepository;
        this.timesheetRepository = timesheetRepository;
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    //  @Recoverable
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Timesheet> getTimesheets(Long id) {
        if (projectRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Project with id = " + id + " does not exists");
        }
        return timesheetRepository.findByProjectId(id);
    }
}
