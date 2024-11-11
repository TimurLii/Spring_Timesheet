//package com.example.spring_timesheet.model.serevice.projectService;
//
//import com.example.spring_timesheet.controller.project.ProjectPageDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//@RequiredArgsConstructor
//public class ProjectPageService {
//    //region Field
////    private final ProjectService projectService;
//
//    //endregion
//
//
//    private ProjectPageDto convert(Project project) {
//        ProjectPageDto projectPageDto = new ProjectPageDto();
//
//        projectPageDto.setId(project.getId());
//        projectPageDto.setNameProject(project.getNameProject());
//
//        return projectPageDto;
//
//    }
//
//    public List<ProjectPageDto> findAll() {
//        return projectService.findAll()
//                .stream().map(this::convert)
//                .toList();
//    }
//
//    public Optional<ProjectPageDto> findById(Long id) {
//        return projectService.findById(id)
//                .map(this::convert);
//    }
//}