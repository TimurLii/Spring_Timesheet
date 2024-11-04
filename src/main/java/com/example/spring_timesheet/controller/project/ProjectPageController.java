package com.example.spring_timesheet.controller.project;

import com.example.spring_timesheet.model.serevice.projectService.ProjectPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectPageController {
    private final ProjectPageService projectPageService;

    public ProjectPageController(ProjectPageService projectPageService) {
        this.projectPageService = projectPageService;
    }

    @GetMapping("/view/{id}")
    public String getProjectsPage(@PathVariable Long id, Model model) {
        Optional<ProjectPageDto> projectPageDto = projectPageService.findById(id);
        if (projectPageDto.isEmpty()) {
            return "not-found.html";
        }
        model.addAttribute("project", projectPageDto.get());
        return "project-page.html";
    }

    @GetMapping()
    public String getAllProjects(Model model) {
        List<ProjectPageDto> projects = projectPageService.findAll();
        model.addAttribute("projects", projects);
        return "projects-page.html";
    }
}