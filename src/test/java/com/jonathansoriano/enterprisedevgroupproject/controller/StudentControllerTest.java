package com.jonathansoriano.enterprisedevgroupproject.controller;

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
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @MockitoBean
    private StudentService service;
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper; // Can be used to serialize/deserialize JSON
                                        // Can also be used to convert Request(StudentSignupRequest,etc) Objects to JSON (POST request body)

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
    void find_Http404_NotFound() throws Exception{
        //Arrange
        //when(service.find(any())).thenReturn(Collections.emptyList());
        when(service.find(any())).thenThrow(SearchNotFoundException.class);
        //Act and Assert (using andExpect() method)
        mockMvc.perform(get("/findStudent")
                .param("firstName", "Grady")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void createNewStudent_Http201_Created() throws Exception {
        //Arrange
        String requestJson = """
                            {
                              "firstName": "Jon",
                              "lastName": "Sanjuan",
                              "residentCity": "Cincinnati",
                              "residentState": "OH",
                              "universityName": "University of Cincinnati",
                              "grade": "Senior",
                              "major": "Computer Science",
                              "email": "jon@example.com",
                              "password": "Password123!",
                              "socialMediaLink": "https://linkedin.com/in/someone"
                            }
                            """;
        String expectedMessage = "Student Signup Successful!";
        when(service.insertNewStudent(any())).thenReturn(expectedMessage);
        //Act and Assert (using andExpect() method)
        mockMvc.perform(post("/studentSignUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isCreated())
                        .andExpect(content().string(expectedMessage));
    }

    @Test
    void createNewStudent_MissingBody_Http400_BadRequest() throws Exception{
        //Arrange
        //Act and Assert (using andExpect() method)
        mockMvc.perform(post("/studentSignUp")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());
    }

    @Test
    void createNewStudent_MissingRequiredField_Http400() throws Exception{
        //Arrange
        //Missing required field (password)
        String invalidRequestJson = """
                                        {
                                          "firstName": "FirstName",
                                          "lastName": "LastName",
                                          "residentCity": "TestCity",
                                          "residentState": "OH",
                                          "universityId": 1,
                                          "grade": "Junior",
                                          "major": "Computer Science",
                                          "email": "test@example.com",
                                          "socialMediaLink": "Test"
                                        }
                                    """;
        //Act and Assert (using andExpect() method)
        mockMvc.perform(post("/studentSignUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestJson))
                                                .andExpect(status().isBadRequest());
    }



    private List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();

        Student student1 = Student.builder()
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