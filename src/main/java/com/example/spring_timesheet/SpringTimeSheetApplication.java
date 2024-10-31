package com.example.spring_timesheet;

import com.example.spring_timesheet.model.Employee;
import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.model.Timesheet;
import com.example.spring_timesheet.repository.EmployeeRepository;
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
        EmployeeRepository employeeRepository = ctx.getBean(EmployeeRepository.class);
        TimesheetRepository timesheetRepository = ctx.getBean(TimesheetRepository.class);
        ProjectRepository projectRepository = ctx.getBean(ProjectRepository.class);

//        Random random = new Random();
//        for (int i = 1; i <= 5; i++) {
//            Project project = new Project();
//            project.setNameProject("Project # " + i);
//            projectRepository.save(project);
//
//            Timesheet timesheet = new Timesheet();
//            LocalDate createdAt = LocalDate.now();
//            createdAt = createdAt.plusDays(1);
//            timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
//            timesheet.setCreatedAt(createdAt);
//            timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
//            timesheetRepository.save(timesheet);
//
//
//            Employee employee = new Employee();
//            employee.setName("Employee # " + i);
//            employee.setProjectId(project.getId());
//            employee.setProjectId((long) ThreadLocalRandom.current().nextInt(1, 6));
//            employee.setTimesheetId(Long.valueOf(ThreadLocalRandom.current().nextInt(1, 6)));
//
//            employeeRepository.save(employee);
//
//
//        }
        for (int i = 1; i <= 5; i++) {
            Project project = new Project();
            project.setNameProject("Project # " + i);
            projectRepository.save(project);
            System.out.println(project);

            for (int j = 0; j < 2; j++) { // Каждому проекту добавляем 2 работника
                Employee employee = new Employee();
                employee.setName("Employee # " + (i * 2 + j));
                employee.setProjectId(project.getId()); // Связываем работника с проектом
                employeeRepository.save(employee);

                for (int k = 0; k < 5; k++) {
                    Timesheet timesheet = new Timesheet();
                    LocalDate createdAt = LocalDate.now();
                    createdAt = createdAt.plusDays(1);
                    timesheet.setProjectId(project.getId());
                    timesheet.setCreatedAt(createdAt);
                    timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
                    timesheet.setEmployeeId(employee.getId());
                    timesheetRepository.save(timesheet);
                    employee.setTimesheetId(timesheet.getId());
                    employeeRepository.save(employee);
                    project.setEmployeeId(employee.getId());
                    projectRepository.save(project);
                    System.out.println(project);

                }
            }
        }
    }
}
