package com.example.spring_timesheet.controller.employee;

import com.example.spring_timesheet.model.Employee;
import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.model.Timesheet;
import com.example.spring_timesheet.model.serevice.employeeService.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    //region Field
    private final EmployeeService employeeService;
    //endregion

    //region Method
    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //получение всех timesheets по сотруднику
//    @GetMapping("/{id}/timesheet")
//    public String getTimesheet(@PathVariable Long id, Model model) {
//        Optional<Employee> employeeList = employeeService.findById(id);
//        if (employeeList.isEmpty()) {
//            return "not-found.html"; // Исправлена опечатка
//        }
//        model.addAttribute("employees", employeeList.get());
//        return "employees-page.html";
//    }
    @GetMapping("/{id}/timesheet")
    public ResponseEntity<List<Timesheet>> getTimesheet(@PathVariable Long id, Model model) {
        try {
            return ResponseEntity.ok(employeeService.getTimesheetByEmployeeId(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<Project>> getProject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(employeeService.findProjectByEmployeeId(id));

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employee));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        employeeService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
    //endregion

}
