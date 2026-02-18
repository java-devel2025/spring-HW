package com.example.hogwarts.service;

import com.example.hogwarts.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.hogwarts.model.Student;
import org.springframework.web.server.ResponseStatusException;

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

    public List<Student> findByAge(int age) {
        return repository.findByAge(age);
    }

    public Collection<Student> getAll() {
        return repository.findAll();
    }
}