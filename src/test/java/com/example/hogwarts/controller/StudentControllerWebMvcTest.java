package com.example.hogwarts.controller;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudent() throws Exception {

        Student student = new Student(1L, "Миша", 11);

        Mockito.when(studentService.get(1L))
                .thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Миша"))
                .andExpect(jsonPath("$.age").value(11));
    }

    @Test
    void getStudentNotFound() throws Exception {
        Mockito.when(studentService.get(1L))
                .thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateStudent() throws Exception {

        Student updated = new Student(1L, "Миша Updated", 12);

        Mockito.when(studentService.update(Mockito.any(Student.class)))
                .thenReturn(updated);

        mockMvc.perform(MockMvcRequestBuilders.put("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "id":1,
                              "name":"Миша Updated",
                              "age":12
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Миша Updated"))
                .andExpect(jsonPath("$.age").value(12));
    }

    @Test
    void deleteStudent() throws Exception {

        Mockito.doNothing().when(studentService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/student/1"))
                .andExpect(status().isOk());

        Mockito.verify(studentService, Mockito.times(1)).delete(1L);
    }

    @Test
    void findByAge() throws Exception {

        List<Student> students = List.of(
                new Student(1L, "Миша", 11),
                new Student(2L, "Ron", 11)
        );

        Mockito.when(studentService.findByAge(11))
                .thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/filter")
                        .param("age", "11"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Миша"))
                .andExpect(jsonPath("$[1].name").value("Ron"));
    }

    @Test
    void getAllStudents() throws Exception {

        List<Student> students = List.of(
                new Student(1L, "Миша", 11),
                new Student(2L, "Hermione", 12)
        );

        Mockito.when(studentService.getAll())
                .thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Миша"))
                .andExpect(jsonPath("$[1].name").value("Hermione"));
    }
}
