package com.example.spring_timesheet.controller.employee;

import lombok.Data;

@Data
public class EmployeePageDto {
    private Long employeeId;
    private String nameEmployee;
    private Long employeeProjectId;
    private Long employeeTimesheetId;

}
