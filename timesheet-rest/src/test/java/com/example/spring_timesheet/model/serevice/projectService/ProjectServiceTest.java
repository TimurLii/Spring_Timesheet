package com.example.spring_timesheet.model.serevice.projectService;

import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("test" )
class ProjectServiceTest {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @Test
    void testFindById() {
//        doReturn(Optional.empty()).when(projectRepository).findById(2l) ;

        assertTrue(projectService.findById(2l).isEmpty());
    }

    @Test
    void findByIdPresent(){
        Project project = new Project();
        project.setNameProject("projectName");
        project = projectRepository.save(project);

        Optional<Project> actual = projectRepository.findById(project.getId());
        assertTrue(actual.isPresent());
        assertEquals(actual.get().getId(), project.getId());
        assertEquals(actual.get().getNameProject(), project.getNameProject());
    }

}