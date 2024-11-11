package com.example.spring_timesheet.model.serevice.employeeService;

//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class  EmployeePageService {
//    //region Field
////    private final EmployeeService employeeService;
//    //endregion
//
//    //region Method
//    private EmployeePageDto convert(Employee employee){
//        EmployeePageDto employeePageDto = new EmployeePageDto();
//
//        employeePageDto.setEmployeeId(employee.getId());
//        employeePageDto.setNameEmployee(employee.getName());
//        employeePageDto.setEmployeeProjectId(employee.getProjectId());
//        employeePageDto.setEmployeeTimesheetId(employee.getTimesheetId());
//        return employeePageDto;
//    }
//
//    public List<EmployeePageDto> findAll(){
//        return employeeService.findAll()
//                .stream().map(this::convert)
//                .toList();
//    }
//
//    public Optional<EmployeePageDto> findById(Long id){
//        return employeeService.findById(id)
//                .map(this::convert);
//    }
//
//    //endregion
//
//
//}
