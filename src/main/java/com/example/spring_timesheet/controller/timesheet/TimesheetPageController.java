package com.example.spring_timesheet.controller.timesheet;


import com.example.spring_timesheet.model.serevice.timesheetSerevice.TimesheetPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {
    private final TimesheetPageService timesheetPageService;

    @GetMapping("/{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model) {
        Optional<TimesheetPageDto> timesheetPageDto = timesheetPageService.findById(id);
        if(timesheetPageDto.isEmpty()){
            return "not-found.html";
        }
        model.addAttribute("timesheet" , timesheetPageDto.get());
        return "timesheet-page.html";
    }

    @GetMapping()
    public String getAllTimesheets(Model model){
        List<TimesheetPageDto> timesheets = timesheetPageService.findAll();

        model.addAttribute("timesheets" , timesheets);
        return "timesheets-page.html";
    }


}