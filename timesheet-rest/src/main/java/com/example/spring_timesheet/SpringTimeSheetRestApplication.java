package com.example.spring_timesheet;

import com.example.spring_timesheet.model.*;
import com.example.spring_timesheet.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringTimeSheetRestApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringTimeSheetRestApplication.class, args);
        EmployeeRepository employeeRepository = ctx.getBean(EmployeeRepository.class);
        TimesheetRepository timesheetRepository = ctx.getBean(TimesheetRepository.class);
        ProjectRepository projectRepository = ctx.getBean(ProjectRepository.class);
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        UserRoleRepository userRoleRepository = ctx.getBean(UserRoleRepository.class);
        RoleRepository roleRepository = ctx.getBean(RoleRepository.class);


        int c = 0;
        while (c <= 5) {
            for (int i = 1; i <= 5; i++) {
                Project project = new Project();
                project.setNameProject("Project # " + i);
                projectRepository.save(project);

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
                        c++;
                    }
                }
            }
        }


        Role roleAdmin = new Role("admin");
        roleAdmin = roleRepository.save(roleAdmin);

        Role roleUser = new Role("user");
        roleUser = roleRepository.save(roleUser);

        createUser(1L, roleRepository, userRepository, "admin", "$2a$12$NGCnVzb3BNhtJHNIE.FZMeLvd7DSMJcOGE6HV.EX1nDKIhRfuAby6");
        createUser(2L, roleRepository, userRepository, "user", "$2a$12$the3O0o2F3KFMN1SmfYvy./Kz7SNMT2FG.78ouzLsrVMuQXhGJe9S");
        createUser(2L, roleRepository, userRepository, "anon", "$2a$12$2cEvpBb5ExHhGu4Bzwww7.1V8s7j7stw1BPPDA0XNmVIGSOnqqBUa");
    }

    public static void createUser(Long roleId, RoleRepository roleRepository, UserRepository userRepository, String login, String password) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));


        User user = new User();
        user.setRole(role);
        user.setLogin(login);
        user.setPassword(password);
        userRepository.save(user);

    }

//    @Recover(noRecoverFor = {NullPointerException.class})
//    public static int examinationAspectRecover(int  a , int b) {
//        return  a / b;
//    }
}