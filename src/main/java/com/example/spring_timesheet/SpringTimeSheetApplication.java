package com.example.spring_timesheet;

import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.model.Timesheet;
import com.example.spring_timesheet.repository.ProjectRepository;
import com.example.spring_timesheet.repository.TimesheetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class SpringTimeSheetApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringTimeSheetApplication.class, args);
        TimesheetRepository timesheetRepository = ctx.getBean(TimesheetRepository.class);
        ProjectRepository projectRepository = ctx.getBean(ProjectRepository.class);

        for(int i = 1; i <= 5; i++){
            Project project = new Project();
            project.setNameProject("Project # " + i);
            projectRepository.save(project);
        }



        LocalDate createdAt = LocalDate.now();
        for(int i = 0; i < 10; i++){
            createdAt = createdAt.plusDays(1);

            Timesheet timesheet = new Timesheet();
            timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1,6));
            timesheet.setCreatedAt(createdAt);
            timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000 ));

            timesheetRepository.save(timesheet);
        }
    }

}
