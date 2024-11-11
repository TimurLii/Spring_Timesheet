package com.example.spring_timesheet.controller.project;

import com.example.spring_timesheet.model.Project;
import com.example.spring_timesheet.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWebTestClient
class ProjectControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ProjectRepository projectRepository;
    private RestClient restClient;


    @BeforeEach
    void beforeEach() {
        restClient = RestClient.create("http://localhost:" + port);
    }

    @Test
    void getByIdAllOk() {

        Project expected = new Project();
        expected.setNameProject("projectName");
        expected = projectRepository.save(expected);

//        RestClient restClient = RestClient.create("http://localhost:" + port);

        ResponseEntity<Project> actual = restClient.get()
                .uri("/projects/" + expected.getId())
                .retrieve()
                .toEntity(Project.class);

//        webTestClient.get()
//                .uri("/projects/" + expected.getId())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(Project.class)
//                .value(actual -> {
//                    assertEquals(expected.getId(), actual.getId());
//                    assertEquals(expected.getNameProject(), actual.getNameProject());
//                });

        assertEquals(HttpStatus.OK, actual.getStatusCode());

        Project responseBody = actual.getBody();
        assertNotNull(responseBody);

    }

    @Test
    void getByIdNotFound() {
        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restClient.get()
                    .uri("projects/-2")
                    .retrieve()
                    .toBodilessEntity();
        });

//        ResponseEntity<Void> response = restClient.get()
//                .uri("projects/-2")
//                .retrieve()
//                .toBodilessEntity();

//         assertEquals(HttpStatus.NOT_FOUND , response.getStatusCode() );
    }

    @Test
    void testCreate() {
        Project toCreate = new Project();
        toCreate.setNameProject("Create projectName");
        Project expected = projectRepository.save(toCreate);

        ResponseEntity<Project> response = restClient.post()
                .uri("/projects")
                .body(toCreate)
                .retrieve()
                .toEntity(Project.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Project responseBody = response.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(responseBody.getNameProject(), toCreate.getNameProject());

        assertTrue(projectRepository.existsById(responseBody.getId()));

    }
    @Test
    void tetDeleteById(){
        Project toDelete = new Project();
        toDelete.setNameProject("Create projectName");
        toDelete = projectRepository.save(toDelete);

        ResponseEntity<Void> response = restClient.delete()
                .uri("/projects/" + toDelete.getId())
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertFalse(projectRepository.existsById(toDelete.getId()));
    }
}