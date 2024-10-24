package com.example.spring_timesheet.model;

import lombok.Data;

@Data
public class Project {
    private Long id;
    private String nameProject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }
}
