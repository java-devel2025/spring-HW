package com.example.hogwarts.controller;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.StudentRepository;
import com.example.hogwarts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudents() {
        return service.getLastFiveStudents();
    }

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students/names-starting-with-a")
    public List<String> getNamesStartingWithA() {
        return studentRepository.findAll().stream()
                .map(student -> student.getName().toUpperCase())
                .filter(name -> name.startsWith("A"))
                .sorted()
                .toList();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

    @GetMapping("/students/print-parallel")
    public void printParallel() {
        List<Student> students = studentRepository.findAll();

        // 1–2 в main thread
        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        // 3–4 в thread1
        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        }).start();

        // 5–6 в thread2
        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        }).start();
    }

    private synchronized void printName(String name) {
        System.out.println(name);
    }

    @GetMapping("/students/print-synchronized")
    public void printSynchronized() {
        List<Student> students = studentRepository.findAll();

        // 1–2 main thread
        printName(students.get(0).getName());
        printName(students.get(1).getName());

        // 3–4 thread1
        new Thread(() -> {
            printName(students.get(2).getName());
            printName(students.get(3).getName());
        }).start();

        // 5–6 thread2
        new Thread(() -> {
            printName(students.get(4).getName());
            printName(students.get(5).getName());
        }).start();

        if (students.size() < 6) {
            throw new RuntimeException("Недостаточно студентов");
        }
    }


}


