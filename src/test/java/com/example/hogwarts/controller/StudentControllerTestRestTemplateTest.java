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
    TestRestTemplate rest;

    private Student createStudent(String name, int age) {
        return rest.postForObject("/student", new Student(null, name, age), Student.class);
    }

    @Test
    void createStudent() {
        Student student = createStudent("Гарик", 11);
        Assertions.assertNotNull(student.getId());
    }

    @Test
    void getStudent() {
        Student created = createStudent("Роман", 12);

        ResponseEntity<Student> response =
                rest.getForEntity("/student/" + created.getId(), Student.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Роман", response.getBody().getName());
    }

    @Test
    void getStudentNotFound() {
        ResponseEntity<Student> response =
                rest.getForEntity("/student/999999", Student.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateStudent() {
        Student created = createStudent("Герман", 12);
        created.setName("Герман Updated");

        rest.put("/student", created);

        Student updated =
                rest.getForObject("/student/" + created.getId(), Student.class);

        Assertions.assertEquals("Герман Updated", updated.getName());
    }

    @Test
    void deleteStudent() {
        Student created = createStudent("Наиль", 11);

        rest.delete("/student/" + created.getId());

        ResponseEntity<Student> response =
                rest.getForEntity("/student/" + created.getId(), Student.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void filterByAge() {
        createStudent("A", 15);
        createStudent("B", 15);

        ResponseEntity<Student[]> response =
                rest.getForEntity("/student/filter?age=15", Student[].class);

        Assertions.assertTrue(response.getBody().length >= 2);
    }

    @Test
    void getAllStudents() {
        createStudent("X", 10);
        createStudent("Y", 11);

        ResponseEntity<Student[]> response =
                rest.getForEntity("/student", Student[].class);

        Assertions.assertTrue(response.getBody().length >= 2);
    }
}