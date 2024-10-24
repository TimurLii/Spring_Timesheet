package com.example.spring_timesheet.controller;

import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.model.serevice.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/project")
public class ProjectController {
    //region Field
    private final ProjectService projectService;
    //endregion

    //region Constructor
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    //endregion

    //region Method get
    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable Long id) {
        Project byId = projectService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byId);
    }
    //endregion

    //region Method getAll
    @GetMapping("/")
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectService.getAll());
    }

    //endregion

    //region Method create
    @PostMapping("/")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = projectService.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    //endregion

    //region Method delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
    //endregion

}
