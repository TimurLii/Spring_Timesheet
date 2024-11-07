package com.example.spring_timesheet.model.serevice.employeeService;

import com.example.spring_timesheet.controller.employee.EmployeePageDto;
import com.example.spring_timesheet.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeePageService {
    //region Field
    private final EmployeeService employeeService;
    //endregion

    //region Method
    private EmployeePageDto convert(Employee employee){
        EmployeePageDto employeePageDto = new EmployeePageDto();

        employeePageDto.setEmployeeId(employee.getId());
        employeePageDto.setNameEmployee(employee.getName());
        employeePageDto.setEmployeeProjectId(employee.getProjectId());
        employeePageDto.setEmployeeTimesheetId(employee.getTimesheetId());
        return employeePageDto;
    }

    public List<EmployeePageDto> findAll(){
        return employeeService.findAll()
                .stream().map(this::convert)
                .toList();
    }

    public Optional<EmployeePageDto> findById(Long id){
        return employeeService.findById(id)
                .map(this::convert);
    }

    //endregion


}
