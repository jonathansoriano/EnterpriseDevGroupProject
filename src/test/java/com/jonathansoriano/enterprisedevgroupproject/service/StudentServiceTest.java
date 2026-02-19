package com.jonathansoriano.enterprisedevgroupproject.service;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentDto;
import com.jonathansoriano.enterprisedevgroupproject.exception.SearchNotFoundException;
import com.jonathansoriano.enterprisedevgroupproject.model.Student;
import com.jonathansoriano.enterprisedevgroupproject.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//This annotation is used to initialize the repository mock and service inject mocks.
//We don't use @ExtendWith(SpringExtension.class) anymore?
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepository repository;

    @InjectMocks
    StudentService service;

    /**
     * This Service Layer test makes sure that service.find(...) method acts as expected, by return a list
     * of type Student
     */
    @Test
    void find_returnsList() {
        //Arrange (What does this method need to make this function?)
        //Request gets passed in by the Controller
        StudentRequest request = StudentRequest.builder()
                .firstName("Rod")
                .lastName("James")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityName("University of Cincinnati")
                .grade("Senior")
                .build();

        StudentDto studentDto = StudentDto.builder()
                .id(1L).firstName("Rod")
                .lastName("James")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityName("University of Cincinnati")
                .grade("Senior")
                .email("rod@mail.edu")
                .socialMediaLink("linkedin.com/rodjames")
                .build();
        //A List of type StudentoDtos gets returned to the Service layer from the Repository
        List<StudentDto> expectDtoList = List.of(studentDto);

        //Mocking up the expected behavior for when the repository.find(...) gets called
        when(repository.find(request)).thenReturn(expectDtoList);

        //Act (Testing the method we are testing and check if what we expect matches with what actually happens
        List<Student> actualList = service.find(request);
        //Assert
        assertEquals(1L, actualList.get(0).getId());
        assertEquals("Rod", actualList.get(0).getFirstName());
        assertEquals("James", actualList.get(0).getLastName());

    }

    /**
     * This Service Layer test makes sure that service.find(...) method returns a custom Exception
     * (SearchNotFoundException) when the user requests a non-existing Student.
     */
    @Test
    void find_returnsException(){
        //Arrange

        //Non-existent Student
        StudentRequest request = StudentRequest.builder()
                .firstName("First")
                .lastName("Last")
                .residentCity("City")
                .residentState("ST")
                .universityName("University")
                .grade("Grade")
                .build();
        //If the repository returns an empty list, we will hit a check if the list is empty, return a
        //SearchNotFoundException
        when(repository.find(request)).thenReturn(Collections.emptyList());

        //Act and Assert (The executable is our act part of the test)
        assertThrows(SearchNotFoundException.class, ()-> service.find(request));

    }


}