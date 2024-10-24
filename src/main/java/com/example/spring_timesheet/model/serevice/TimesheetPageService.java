package com.example.spring_timesheet.model.serevice;

import com.example.spring_timesheet.controller.TimesheetPageDto;
import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.model.Timesheet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {
    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    public Optional<TimesheetPageDto> findById(Long id) {
        return timesheetService.findById(id)
                .map(this::convert);
    }

    private TimesheetPageDto convert(Timesheet timesheet) {

        Project project = projectService.findById(timesheet.getProjectId())
                .orElseThrow();

        TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
        timesheetPageDto.setProjectName(project.getNameProject());
        timesheetPageDto.setTimesheetId(String.valueOf(timesheet.getId()));
        timesheetPageDto.setTimesheetMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDto.setTimesheetCreatedAt(String.valueOf(timesheet.getCreatedAt()));

        return timesheetPageDto;
    }

    public List<TimesheetPageDto> findAll() {
        return timesheetService.findAll().stream()
                .map(this::convert)
                .toList();
    }
}
