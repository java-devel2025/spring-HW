package com.example.hogwarts.controller;
import com.example.hogwarts.model.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void createFaculty() {
        Faculty faculty = new Faculty(null,"Ravenclaw","Blue");

        ResponseEntity<Faculty> response =
                rest.postForEntity("/faculty", faculty, Faculty.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getFacultyNotFound() {
        ResponseEntity<Faculty> response =
                rest.getForEntity("/faculty/999999", Faculty.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
