package com.example.hogwarts.controller;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService service;
    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return service.create(faculty);
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return service.update(faculty);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/filter")
    public List<Faculty> findByColor(@RequestParam String color) {
        return service.findByColor(color);
    }

    @GetMapping
    public Collection<Faculty> getAll() {
        return service.getAll();
    }
}
