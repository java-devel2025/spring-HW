package com.example.hogwarts.controller;
import com.example.hogwarts.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void createAndGetStudent() {
        Student request = new Student(null,"Harry",11);

        Student created = rest.postForObject("/student", request, Student.class);

        ResponseEntity<Student> response =
                rest.getForEntity("/student/" + created.getId(), Student.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Harry", response.getBody().getName());
    }

    @Test
    void getAllStudents() {
        ResponseEntity<Student[]> response =
                rest.getForEntity("/student", Student[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void filterStudents() {
        ResponseEntity<Student[]> response =
                rest.getForEntity("/student/filter?age=11", Student[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteStudent() {
        Student s = rest.postForObject("/student", new Student(null,"Ron",11), Student.class);

        rest.delete("/student/" + s.getId());

        ResponseEntity<Student> response =
                rest.getForEntity("/student/" + s.getId(), Student.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}