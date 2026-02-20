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
    TestRestTemplate rest;

    private Faculty createFaculty(String name, String color) {
        return rest.postForObject("/faculty", new Faculty(null,name,color), Faculty.class);
    }

    @Test
    void getFacultyNotFound() {
        ResponseEntity<Faculty> response =
                rest.getForEntity("/faculty/99999", Faculty.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void filterByColor() {
        createFaculty("Gryffindor","Red");

        ResponseEntity<Faculty[]> response =
                rest.getForEntity("/faculty/filter?color=Red", Faculty[].class);

        Assertions.assertTrue(response.getBody().length >= 1);
    }
}
