package com.example.spring_timesheet.controller.timesheet;

import com.example.spring_timesheet.model.Timesheet;
import com.example.spring_timesheet.repository.TimesheetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeSheetControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    TimesheetRepository timesheetRepository;
    private RestClient restClient;


    @BeforeEach
    void beforeEach() {
        restClient = RestClient.create("http://localhost:" + port);
    }
    @Test
    void testGetByIdAllOk() {

        Timesheet expected = createTimesheetObject();
        ResponseEntity<Timesheet> actual = restClient.get()
                .uri("/timesheet/" + expected.getId())
                .retrieve()
                .toEntity(Timesheet.class);


        assertEquals(HttpStatus.OK, actual.getStatusCode());

        Timesheet responseBody = actual.getBody();
        assertNotNull(responseBody);
    }
    @Test
    void testGetAllOk() {
        int countElement = 5;
        List<Timesheet> expected = createTimesheetObjectAsList(countElement);
        ResponseEntity<List<Timesheet>> actual = restClient.get()
                .uri("/timesheet")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Timesheet>>() {
                });

        assertNotNull(actual);
        assertEquals(expected, actual.getBody());
        for (int i = 0; i <= countElement - 1; i++) {
            assertEquals(expected.get(i), actual.getBody().get(i));
        }


    }
    @Test
    void testCreate() {
        Timesheet expected = createTimesheetObject();
//        ResponseEntity<Timesheet> response = restClient.get()
//                .uri("timesheet/" + expected.getId())
//                .retrieve()
//                .toEntity(Timesheet.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
        webTestClient.get()
                .uri("/timesheet/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Timesheet.class)
                .value(actual -> {
                    assertEquals(expected.getId(), actual.getId());
                    assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
                    assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
                    assertEquals(expected.getProjectId(), actual.getProjectId());
                    assertEquals(expected.getMinutes(), actual.getMinutes());
                });
    }
    @Test
    void testDeleteById() {

        Timesheet toDelete = createTimesheetObject();

        ResponseEntity<Void> response = restClient.get()
                .uri("/timesheet/" + toDelete.getId())
                .retrieve()
                .toBodilessEntity();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(timesheetRepository.existsById(toDelete.getId()));


    }
    @Test
    void testChangeTimesheet(){
        Timesheet timesheet = createTimesheetObject();
        System.err.println(timesheet.getProjectId());
        timesheetRepository.save(timesheet);

        ResponseEntity<Timesheet> response = restClient.get()
                .uri("/timesheet/" + timesheet.getId())
                .retrieve()
                .toEntity(Timesheet.class);

        assertEquals(timesheet.getProjectId(), response.getBody().getProjectId());

    }


    public Timesheet createTimesheetObject() {
        Timesheet timesheet = new Timesheet();

        timesheet.setId((long) 1);
        timesheet.setCreatedAt(LocalDate.now());
        timesheet.setMinutes(1);
        timesheet.setEmployeeId((long) 1);
        timesheet.setProjectId((long) 1);
        timesheetRepository.save(timesheet);
        return timesheet;
    }

    public List<Timesheet> createTimesheetObjectAsList(int i) {
        List<Timesheet> list = new ArrayList<>();

        int k = 1;
        while (k <= i) {

            Timesheet timesheet = new Timesheet();
            timesheet.setId((long) k);
            timesheet.setCreatedAt(LocalDate.now());
            timesheet.setMinutes(k);
            timesheet.setEmployeeId((long) k);
            timesheet.setProjectId((long) k);
            list.add(timesheet);
            timesheetRepository.save(timesheet);
            k++;
        }
        return list;
    }
}