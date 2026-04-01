package com.jonathansoriano.enterprisedevgroupproject.service;

import com.jonathansoriano.enterprisedevgroupproject.domain.EditStudentDetailsRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentAccountDetailsDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentUpdateDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.UserDto;
import com.jonathansoriano.enterprisedevgroupproject.exception.EmailAlreadyExistsException;
import com.jonathansoriano.enterprisedevgroupproject.exception.SearchNotFoundException;
import com.jonathansoriano.enterprisedevgroupproject.model.Student;
import com.jonathansoriano.enterprisedevgroupproject.model.StudentAccountDetails;
import com.jonathansoriano.enterprisedevgroupproject.repository.StudentRepository;
import com.jonathansoriano.enterprisedevgroupproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//This annotation is used to initialize the repository mock and service inject mocks.
//We don't use @ExtendWith(SpringExtension.class) anymore?
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;
    @Mock
    UserRepository userRepository;

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
        when(studentRepository.find(request)).thenReturn(expectDtoList);

        //Act (Testing the method we are testing and check if what we expect matches with what actually happens
        List<Student> actualList = service.find(request);
        //Assert
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
        when(studentRepository.find(request)).thenReturn(Collections.emptyList());

        //Act and Assert (The executable is our act part of the test)
        assertThrows(SearchNotFoundException.class, ()-> service.find(request));

    }

    /**
     * This Service Layer test makes sure that service.insertNewStudent(...) method acts as expected (happy path), by returning a String message
     * saying that the student signup was successful.
     */
    @Test
    void insertNewStudent_returnsMessage() {
        //Arrange
        //This method takes in a StudentSignupRequest object to start the process of inserting a new student and new user
        StudentSignupRequest studentSignupRequest = StudentSignupRequest.builder()
                .firstName("Jonatan")
                .lastName("Sanjuan")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1)
                .grade("Senior")
                .major("Information Technology")
                .email("sorianjn@uc.mail.edu")
                .password("passw0rd!")
                .socialMediaLink("linkedin.com/sorianjn")
                .build();

        UserDto userDto = null;

        // Expected Responses from the StudentRepository and UserRepository
        int expectedResponseFromStudentRepository = 1;
        int expectedResponseFromUserRepository = 1;

        // Mocking up the expected behavior for when the repository.insertNewStudent(...) gets called and when the repository.insertNewUser(...) gets called
        when(userRepository.findByEmail(studentSignupRequest.getEmail())).thenReturn(Optional.ofNullable(userDto));
        when(studentRepository.insertNewStudent(studentSignupRequest)).thenReturn(expectedResponseFromStudentRepository);
        when(userRepository.insertNewUser(any())).thenReturn(expectedResponseFromUserRepository);

        //Act
        String actualReturnValueFromInsertNewStudent = service.insertNewStudent(studentSignupRequest);
        //Assert
        assertEquals("Student Signup Successful!", actualReturnValueFromInsertNewStudent);
    }

    @Test
    void insertNewStudent_returnsException(){
        //Arrange
        StudentSignupRequest studentSignupRequest = StudentSignupRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1)
                .grade("Senior")
                .major("Information Technology")
                .email("duplicate@mail.uc.edu")
                .password("passw0rd!")
                .socialMediaLink("linkedin.com/sorianjn")
                .build();

        UserDto userDto = UserDto.builder()
                .id(1L)
                .role("USER")
                .email("duplicate@mail.uc.edu")
                .password("passw0rd!")
                .build();

        when(userRepository.findByEmail(studentSignupRequest.getEmail())).thenReturn(Optional.of(userDto));

        //Act & Assert
        assertThrows(EmailAlreadyExistsException.class, ()-> service.insertNewStudent(studentSignupRequest));
    }

    @Test
    void findByEmail_returnsStudentAccountDetails() {
        //Arrange
        String existingUserName = "sarah.johnson@mail.uc.edu";

        StudentAccountDetailsDto studentAccountDetailsDto = StudentAccountDetailsDto.builder()
                .id(1L)
                .firstName("Sarah")
                .lastName("Johnson")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityName("University of Cincinnati")
                .grade("Junior")
                .major("Computer Science")
                .email("sarah.johnson@mail.uc.edu")
                .socialMediaLink("https://linkedin.com/in/sarahjohnson")
                .build();

        StudentAccountDetails expectedStudentAccountDetailsDto = StudentAccountDetails.builder()
                .firstName("Sarah")
                .lastName("Johnson")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityName("University of Cincinnati")
                .grade("Junior")
                .major("Computer Science")
                .email("sarah.johnson@mail.uc.edu")
                .socialMediaLink("https://linkedin.com/in/sarahjohnson")
                .build();

        when(studentRepository.findByEmail(existingUserName)).thenReturn(Optional.of(studentAccountDetailsDto));
        //Act
        StudentAccountDetails actualStudentAccountDetails = service.findByEmail(existingUserName);
        //Assert
        assertEquals(expectedStudentAccountDetailsDto, actualStudentAccountDetails);

    }

    @Test
    void findByEmail_notFound(){
        //Arrange
        String nonExistingUserName = "towelie@mail.edu";

        when(studentRepository.findByEmail(nonExistingUserName)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(SearchNotFoundException.class, ()-> service.findByEmail(nonExistingUserName));
    }

    @Test
    void updateStudent_returnsSuccessfulInsertionMessageWithMultipleNewFields(){
        //Arrange (method takes in these two arguments passed in from Controller: String username, EditStudentDetailsRequest studentDetails.
        String validUserName = "sarah.johnson@mail.uc.edu";

        EditStudentDetailsRequest editStudentDetailsRequest = EditStudentDetailsRequest.builder()
                .firstName("Sara")
                .lastName("Johnsen")
                .residentCity("Mason")
                .residentState("OH")
                .universityId(1)
                .grade("Senior")
                .major("IT")
                .email("sarah.johnson@mail.uc.edu") //email doesn't get updated by Users
                .password("password")
                .socialMediaLink("https://linkedin.com/in/sarajohnsen")
                .build();

        StudentUpdateDto outdatedStudentUpdateDto = StudentUpdateDto.builder()
                .id(1L)
                .firstName("Sarah")
                .lastName("Johnson")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1)
                .grade("Junior")
                .major("Computer Science")
                .email("sarah.johnson@mail.uc.edu")
                .socialMediaLink("https://linkedin.com/in/sarahjohnson")
                .build();

        UserDto outdatedUserDto = UserDto.builder()
                .id(1L)
                .role("USER")
                .email("sarah.johnson@mail.uc.edu")
                .password("$2a$10$cT37ge3YHk2NxIjDvUpns.CucoBA8cQ.DzJXoqcIVJ6nQUZpB9SVa")
                .build();

        when(studentRepository.findStudentByEmail(validUserName)).thenReturn(Optional.of(outdatedStudentUpdateDto));
        when(userRepository.findByEmail(validUserName)).thenReturn(Optional.of(outdatedUserDto));

        //any() is pointing to the updatedStudent and updatedUser, once we set the new fields to both dtos.
        when(studentRepository.updateStudent(any())).thenReturn(1);
        when(userRepository.updateUser(any())).thenReturn(1);

        String expectedInsertionMessage = "Account Updated Successfully!";
        //Act
        String actualInsertionMessage = service.updateStudent(validUserName, editStudentDetailsRequest);
        //Assert
        assertEquals(expectedInsertionMessage, actualInsertionMessage);
    }

    @Test
    void updateStudent_returnsSearchNotFoundExceptionForStudent(){
        //Arrange (method takes in these two arguments passed in from Controller: String username, EditStudentDetailsRequest studentDetails.
        String nonExistingUserName = "johndoe@mail.edu";

        EditStudentDetailsRequest editStudentDetailsRequest = EditStudentDetailsRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .residentCity("Mason")
                .residentState("OH")
                .universityId(1)
                .grade("Senior")
                .major("IT")
                .email("johndoe@mail.edu") //email doesn't get updated by Users
                .password("password")
                .socialMediaLink("https://linkedin.com/in/jdoe")
                .build();

        when(studentRepository.findStudentByEmail(nonExistingUserName)).thenReturn(Optional.empty());
        //when(userRepository.findByEmail(nonExistingUserName)).thenReturn(Optional.empty()); Probably wont get hit if the student table doesn't find student by email first.


        //Act & Assert
        assertThrows(SearchNotFoundException.class, () -> service.updateStudent(nonExistingUserName, editStudentDetailsRequest));
    }

    @Test
    void updateStudent_returnsSearchNotFoundExceptionForUser(){
        //Arrange (method takes in these two arguments passed in from Controller: String username, EditStudentDetailsRequest studentDetails.
        String nonExistingUserNameForUserTable = "johndoe@mail.edu";

        EditStudentDetailsRequest editStudentDetailsRequest = EditStudentDetailsRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .residentCity("Mason")
                .residentState("OH")
                .universityId(1)
                .grade("Senior")
                .major("IT")
                .email("johndoe@mail.edu") //email doesn't get updated by Users
                .password("password")
                .socialMediaLink("https://linkedin.com/in/jdoe")
                .build();

        StudentUpdateDto outdatedStudentDto = StudentUpdateDto.builder()
                .id(100L)
                .firstName("John")
                .lastName("Doe")
                .residentCity("Mason")
                .residentState("OH")
                .universityId(1)
                .grade("Junior")
                .major("Computer Science")
                .email("johndoe@mail.edu")
                .socialMediaLink(null)
                .build();

        when(studentRepository.findStudentByEmail(nonExistingUserNameForUserTable)).thenReturn(Optional.of(outdatedStudentDto));
        when(userRepository.findByEmail(nonExistingUserNameForUserTable)).thenReturn(Optional.empty());


        //Act & Assert
        assertThrows(SearchNotFoundException.class, () -> service.updateStudent(nonExistingUserNameForUserTable, editStudentDetailsRequest));
    }

}