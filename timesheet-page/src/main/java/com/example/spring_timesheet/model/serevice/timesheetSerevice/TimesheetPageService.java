package com.example.spring_timesheet.model.serevice.timesheetSerevice;

import com.example.spring_timesheet.client.ProjectResponse;
import com.example.spring_timesheet.client.TimesheetResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimesheetPageService {
    private RestClient restClient;

    public TimesheetPageService() {
        this.restClient = RestClient.create("http://localhost:8080");
    }

    public Optional<TimesheetPageDto> findById(Long id) {
        try {
            TimesheetResponse timesheets = restClient.get()
                    .uri("/timesheet/" + id)
                    .retrieve()
                    .body(TimesheetResponse.class);
            TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
            timesheetPageDto.setTimesheetId(String.valueOf(timesheets.getId()));
            timesheetPageDto.setTimesheetMinutes(String.valueOf(timesheets.getMinutes()));
            timesheetPageDto.setTimesheetCreatedAt(timesheets.getCreatedAt().format(DateTimeFormatter.ISO_DATE));
            timesheetPageDto.setProjectId(timesheets.getProjectId());

            ProjectResponse project = restClient.get()
                    .uri("/projects/" + timesheets.getProjectId())
                    .retrieve()
                    .body(ProjectResponse.class);
            timesheetPageDto.setProjectName(project.getNameProject());
            return Optional.of(timesheetPageDto);
        }catch (HttpClientErrorException.NotFound e){
            return Optional.empty();
        }
    }



    public List<TimesheetPageDto> findAll() {
        List<TimesheetResponse> timesheets = restClient.get()
                .uri("/timesheet")
                .retrieve()
                .body(new ParameterizedTypeReference<List<TimesheetResponse>>() {
                });
        List<TimesheetPageDto> result = new ArrayList<>();
        for (TimesheetResponse timesheet : timesheets) {
            TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
            timesheetPageDto.setTimesheetId(String.valueOf(timesheet.getId()));
            timesheetPageDto.setTimesheetMinutes(String.valueOf(timesheet.getMinutes()));
            timesheetPageDto.setTimesheetCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));
            timesheetPageDto.setProjectId(timesheet.getProjectId());

            ProjectResponse project = restClient.get()
                    .uri("/projects/" + timesheet.getProjectId())
                    .retrieve()
                    .body(ProjectResponse.class);
            timesheetPageDto.setProjectName(project.getNameProject());
            result.add(timesheetPageDto);
        }
        return result;
    }

}
