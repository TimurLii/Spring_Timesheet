package com.example.spring_timesheet.controller.employee;


import com.example.spring_timesheet.model.serevice.employeeService.EmployeePageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeePageController {
    private final EmployeePageService employeePageService;

        @GetMapping("/view/{id}") // Измененный маршрут
    public String getEmployeesPage(@PathVariable Long id, Model model) {
        Optional<EmployeePageDto> employeePageDto = employeePageService.findById(id);
        if (employeePageDto.isEmpty()) {
            return "not-found.html"; // Исправлена опечатка
        }
        model.addAttribute("employee", employeePageDto.get());
        return "employee-page.html";
    }

    @GetMapping()
    public String getAllEmployees(Model model) {
        List<EmployeePageDto> employees = employeePageService.findAll();
        model.addAttribute("employees", employees); // Здесь вы передаете список сотрудников
        return "employees-page"; // Название шаблона без .html
    }
}
