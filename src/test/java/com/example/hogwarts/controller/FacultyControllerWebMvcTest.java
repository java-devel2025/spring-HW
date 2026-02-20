package com.example.hogwarts.controller;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    void getFaculty() throws Exception {
        Faculty faculty = new Faculty(1L, "Gryffindor", "Red");

        Mockito.when(facultyService.get(1L)).thenReturn(faculty);

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void getFacultyNotFound() throws Exception {
        Mockito.when(facultyService.get(1L)).thenReturn(null);

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllFaculties() throws Exception {
        List<Faculty> list = List.of(
                new Faculty(1L,"Gryffindor","Red"),
                new Faculty(2L,"Slytherin","Green")
        );

        Mockito.when(facultyService.getAll()).thenReturn(list);

        mockMvc.perform(get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void filterByColor() throws Exception {
        List<Faculty> list = List.of(new Faculty(1L,"Gryffindor","Red"));

        Mockito.when(facultyService.findByColor("Red")).thenReturn(list);

        mockMvc.perform(get("/faculty/filter").param("color","Red"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gryffindor"));
    }

    @Test
    void deleteFaculty() throws Exception {
        Mockito.doNothing().when(facultyService).delete(1L);

        mockMvc.perform(delete("/faculty/1"))
                .andExpect(status().isOk());

        Mockito.verify(facultyService).delete(1L);
    }
}
