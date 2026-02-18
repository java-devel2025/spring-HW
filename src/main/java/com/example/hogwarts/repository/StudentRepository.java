package com.example.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hogwarts.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAgeBetween(int min, int max);
    List<Student> findByAge(int age);
}
