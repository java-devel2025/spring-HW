package com.example.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hogwarts.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findByColor(String color);

}

