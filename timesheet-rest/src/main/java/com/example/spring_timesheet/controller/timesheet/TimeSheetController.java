package com.example.spring_timesheet.controller.timesheet;

import com.example.spring_timesheet.model.Timesheet;
import com.example.spring_timesheet.model.serevice.timesheetSerevice.TimesheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timesheet") // /timesheet будет добавлять в каждый адрес
public class TimeSheetController {
    //region Field
    private final TimesheetService timesheetService;
    //endregion

    //region Constructor
    public TimeSheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }
    //endregion

    //region Method get
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Timesheet>> get(@PathVariable Long id) {
        Optional<Timesheet> byId = timesheetService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byId);
    }
    //endregion

    //region Method getAll
    @GetMapping()
    public ResponseEntity<List<Timesheet>> getAll(
            @RequestParam(required = false) LocalDate createdAtBefore
            , @RequestParam(required = false) LocalDate localAtAfter
    ) {
        return ResponseEntity.ok(timesheetService.findAll());
    }

    //endregion

    //region Method create
    @PostMapping("/")
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        timesheet = timesheetService.create(timesheet);
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    //endregion

    //region Method delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        timesheetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //endregion


}
