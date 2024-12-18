package com.example.spring_timesheet.repository;

import com.example.spring_timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByProjectId(Long id );
    @Query(value = "select t from Timesheet t   where t.projectId = :projectId order by t.createdAt desc")
    List<Timesheet>findByProjectSorted(Long projectId);


    List<Timesheet> findByEmployeeId(Long id);



}
