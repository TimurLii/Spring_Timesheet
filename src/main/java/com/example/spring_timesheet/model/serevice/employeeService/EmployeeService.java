package com.example.spring_timesheet.model.serevice.employeeService;

import com.example.spring_timesheet.model.Employee;
import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.model.Timesheet;
import com.example.spring_timesheet.repository.EmployeeRepository;
import com.example.spring_timesheet.repository.ProjectRepository;
import com.example.spring_timesheet.repository.TimesheetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    //region Field
    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;
    private final EmployeeRepository employeeRepository;
    //endregion

    //region Method
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Timesheet> getTimesheetByEmployeeId(Long employeeId) {

        return timesheetRepository.findByEmployeeId(employeeId);
    }

    public List<Project> findProjectByEmployeeId(Long employeeId) {
        return projectRepository.findProjectsByEmployeeId(employeeId);
    }


    //endregion


}
