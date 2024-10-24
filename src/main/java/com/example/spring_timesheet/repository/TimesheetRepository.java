package com.example.spring_timesheet.repository;

import com.example.spring_timesheet.model.Timesheet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class TimesheetRepository {
    private static Long sequence = 1l;
    private final List<Timesheet> timesheets = new ArrayList<>();


    public Timesheet getBuID(Long id) {
        Timesheet timesheet = timesheets.stream()
                .filter(it -> Objects.equals((Long)it.getId(), id))
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


    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        Predicate<Timesheet> filter= it->true;

        if(Objects.nonNull(createdAtBefore)){
            filter = filter.and(it->it.getCreatedAt().isBefore(createdAtBefore));
        }
        if(Objects.nonNull(createdAtAfter)){
            filter = filter.and(it->it.getCreatedAt().isBefore(createdAtAfter));
        }
        return timesheets.stream()
                .filter(filter)
                .toList();
    }
}
