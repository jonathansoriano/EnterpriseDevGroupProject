package com.jonathansoriano.enterprisedevgroupproject.controller;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.exception.SearchNotFoundException;
import com.jonathansoriano.enterprisedevgroupproject.model.Student;
import com.jonathansoriano.enterprisedevgroupproject.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @MockitoBean
    private StudentService service;
    @Autowired
    private MockMvc mockMvc;

    //We test for exceptions in the controller layer to verify the exception is translated to the
    //correct HTTP response (Status code, body, headers)
    @Test
    void find_Http200() throws Exception {
        // Arrange
        List<Student> expectedList = getStudentList();
        when(service.find(any())).thenReturn(expectedList);

        //Act and Assert (andExpect() is our assertions)
        mockMvc.perform(get("/findStudent")
                .param("firstName", "Jo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Jon"))
                .andExpect(jsonPath("$[1].firstName").value("John"))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void find_Http404() throws Exception{
        //Arrange
        //when(service.find(any())).thenReturn(Collections.emptyList());
        when(service.find(any())).thenThrow(SearchNotFoundException.class);
        //Act and Assert (using andExpect() method)
        mockMvc.perform(get("/findStudent")
                .param("firstName", "Grady")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
    

    private List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();

        Student student1 = Student.builder()
                .id(1L)
                .firstName("Jon")
                .lastName("Sanjuan")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityName("University of Cincinnati")
                .grade("Senior")
                .email("jon@mail.com")
                .socialMediaLink("linkedin.com/sorianjn")
                .build();

        Student student2 = Student.builder()
                .id(2L)
                .firstName("John")
                .lastName("Saint")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityName("University of Cincinnati")
                .grade("Junior")
                .email("john@mail.com")
                .socialMediaLink("linkedin.com/sorianjn")
                .build();

        studentList.add(student1);
        studentList.add(student2);

        return studentList;
    }

}