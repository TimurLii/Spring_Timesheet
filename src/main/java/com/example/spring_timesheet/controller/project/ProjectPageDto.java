package com.example.spring_timesheet.controller.project;

public class ProjectPageDto {
    //region Field
    private Long projectId;
    private String nameProject;
    //endregion

    //region Getter/Setter

    public Long getId() {
        return projectId;
    }

    public void setId(Long id) {
        this.projectId = id;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }


    //endregion

}
