package com.example.spring_timesheet.model.serevice;

import com.example.spring_timesheet.model.Timesheet;
import com.example.spring_timesheet.repository.ProjectRepository;
import com.example.spring_timesheet.repository.TimesheetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class TimesheetService {
    private final  TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
    }

    public List<Timesheet> getAll() {
        return timesheetRepository.getAll();
    }
    public Timesheet getById(Long id) {
        return timesheetRepository.getBuID(id);
    }
    public Timesheet create(Timesheet timesheet) {
        if(projectRepository.find(timesheet.getProjectId())!= null){
            return timesheetRepository.create(timesheet);
        }
        return null;
    }
    public void delete(Long id) {
         timesheetRepository.delete(id);
    }


    public Optional<Timesheet> findById (Long id) {
        return Optional.ofNullable(timesheetRepository.getBuID(id));
    }
    public List<Timesheet> findAll(LocalDate createdAtBefore,LocalDate createdAtAfter){
        return timesheetRepository.findAll(createdAtBefore,createdAtAfter);
    }
    public List<Timesheet> findAll(){
        return findAll(null,null);
    }
}
