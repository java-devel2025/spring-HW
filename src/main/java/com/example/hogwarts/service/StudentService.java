package com.example.hogwarts.service;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.example.hogwarts.model.Student;
import java.util.*;


@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Student update(Student student) {
        return repository.save(student);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        return repository.findByAgeBetween(min, max);
    }

    public List<Student> findByAge(int age) {
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

}