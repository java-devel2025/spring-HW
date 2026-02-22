package com.example.hogwarts.controller;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = service.get(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return service.update(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/filter")
    public List<Student> findByAge(@RequestParam int age) {
        return service.findByAge(age);
    }

    @GetMapping
    public Collection<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/age-between")
    public List<Student> findByAgeBetween(@RequestParam int min,
                                          @RequestParam int max) {
        return service.findByAgeBetween(min, max);
    }

    @GetMapping("/{id}/faculty")
    public Faculty getFaculty(@PathVariable Long id) {
        return service.getFaculty(id);
    }

    @GetMapping("/count")
    public long getStudentsCount() {
        return service.getStudentsCount();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return service.getAverageAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudents() {
        return service.getLastFiveStudents();
    }
}


