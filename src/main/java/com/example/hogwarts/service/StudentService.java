package com.example.hogwarts.service;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.example.hogwarts.model.Student;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student student) {
        logger.info("Was invoked method for create student");
        return repository.save(student);
    }

    public Student get(Long id) {
        logger.info("Was invoked method for get student");

        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("No student with id = " + id);
                    return new RuntimeException("Student not found");
                });
    }

    public Student update(Student student) {
        return repository.save(student);
    }

    public void delete(Long id) {
        logger.info("Was invoked method for delete student");

        if (!repository.existsById(id)) {
            logger.warn("Trying to delete non-existing student with id = {}", id);
            throw new RuntimeException("Student not found");
        }

        repository.deleteById(id);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        return repository.findByAgeBetween(min, max);
    }

    public List<Student> findByAge(int age) {
        logger.info("Was invoked method for find students by age");
        logger.debug("Searching students with age = {}", age);

        return repository.findByAge(age);
    }

    public Collection<Student> getAll() {
        return repository.findAll();
    }

    public Faculty getFaculty(Long studentId) {
        return repository.findById(studentId)
                .map(Student::getFaculty)
                .orElse(null);
    }

    public long getStudentsCount() {
        return repository.getStudentsCount();
    }

    public double getAverageAge() {
        Double avg = repository.getAverageAge();
        return avg == null ? 0.0 : avg;
    }

    public List<Student> getLastFiveStudents() {
        return repository.getLastFiveStudents();
    }

}