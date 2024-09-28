package com.example.spring_timesheet.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Timesheet {
    private long id;
    private Long projectId;
    private int minutes;
    private LocalDate createdAt;
}
