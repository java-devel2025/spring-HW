package com.example.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hogwarts.model.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAgeBetween(int min, int max);
    List<Student> findByAge(int age);

    @Query("SELECT COUNT(s) FROM Student s")
    long getStudentsCount();

    @Query("SELECT AVG(s.age) FROM Student s")
    Double getAverageAge();

    @Query(value = """
        SELECT * FROM student
        ORDER BY id DESC
        LIMIT 5
        """, nativeQuery = true)
    List<Student> getLastFiveStudents();


}
