package com.example.spring_timesheet.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TimesheetPageDto {
   private  String projectName;
    private  String timesheetId;
    private  String timesheetMinutes;
    private   String timesheetCreatedAt;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(String timesheetId) {
        this.timesheetId = timesheetId;
    }

    public String getTimesheetMinutes() {
        return timesheetMinutes;
    }

    public void setTimesheetMinutes(String timesheetMinutes) {
        this.timesheetMinutes = timesheetMinutes;
    }

    public String getTimesheetCreatedAt() {
        return timesheetCreatedAt;
    }

    public void setTimesheetCreatedAt(String timesheetCreatedAt) {
        this.timesheetCreatedAt = timesheetCreatedAt;
    }
}
