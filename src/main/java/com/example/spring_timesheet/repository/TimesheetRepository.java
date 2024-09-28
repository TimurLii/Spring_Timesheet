package com.example.spring_timesheet.repository;

import com.example.spring_timesheet.model.Timesheet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Component
public class TimesheetRepository {
    private static long sequence = 1l;
    private final List<Timesheet> timesheets = new ArrayList<>();


    public Timesheet getBuID(long id) {
        Timesheet timesheet = timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .get();

        return timesheet;
    }

    public List<Timesheet> getAll() {
        return List.copyOf(timesheets);
    }

    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
        timesheet.setCreatedAt(LocalDate.now());
        timesheets.add(timesheet);
        return timesheet;
    }

    public ResponseEntity<Void> delete(long id) {
        timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(timesheets::remove);
        return ResponseEntity.noContent().build();
    }
}
