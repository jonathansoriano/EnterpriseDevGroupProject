package com.jonathansoriano.enterprisedevgroupproject.repository;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.UserRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    void find_All() {
        //Arrange
        StudentRequest request = StudentRequest.builder()
                                                .build();

        //Act
        List<StudentDto> actualList = studentRepository.find(request);
        //Assert (33 Total students in the h2 db, so I'm expecting 33 items in the list)
        assertEquals(33, actualList.size());
    }

    @Test
    void find_NotFound(){
        //Arrange (Create a request of a non-existing student in the h2 db)
        StudentRequest request = StudentRequest.builder()
                .firstName("Toyota")
                .lastName("Supra")
                .residentCity("Tokyo")
                .residentState("JP")
                .universityName("Japan University")
                .grade("Senior")
                .build();

        //Act
        List<StudentDto> actualList = studentRepository.find(request);
        //Assert
        assertTrue(CollectionUtils.isEmpty(actualList));

    }

    @ParameterizedTest
    @MethodSource("variousInputForFirstName")
    void find_SearchByFirstName(
            String firstName,
            int expectSize
    ){
        //Arrange
        StudentRequest request = StudentRequest.builder()
                .firstName(firstName)
                .build();
        //Act
        List<StudentDto> actualList = studentRepository.find(request);
        //Assert
        assertEquals(expectSize, actualList.size());

    }

    private static Stream<Arguments> variousInputForFirstName(){
        return Stream.of(
                Arguments.of("James", 1), //Normal Case
                Arguments.of("james", 1), // Lowercase Case
                Arguments.of("JAMES", 1), // Uppercase Case
                Arguments.of("J", 4) // Partial Case

        );
    }

    @ParameterizedTest
    @MethodSource("variousInputForLastName")
    void find_SearchByLastName(
            String lastName,
            int expectSize
    ){
        //Arrange
        StudentRequest request = StudentRequest.builder()
                .lastName(lastName)
                .build();
        //Act
        List<StudentDto> actualList = studentRepository.find(request);
        //Assert
        assertEquals(expectSize, actualList.size());

    }

    private static Stream<Arguments> variousInputForLastName(){
        return Stream.of(
                Arguments.of("Wilson", 1), //Normal Case
                Arguments.of("wilson", 1), // Lowercase Case
                Arguments.of("WILSON", 1), // Uppercase Case
                Arguments.of("w", 7) // Partial Case

        );
    }

    @ParameterizedTest
    @MethodSource("variousInputForCity")
    void find_SearchByCity(
            String city,
            int expectSize
    ){
        //Arrange
        StudentRequest request = StudentRequest.builder()
                .residentCity(city)
                .build();
        //Act
        List<StudentDto> actualList = studentRepository.find(request);
        //Assert
        assertEquals(expectSize, actualList.size());

    }

    private static Stream<Arguments> variousInputForCity(){
        return Stream.of(
                Arguments.of("Covington", 1), //Normal Case
                Arguments.of("covington", 1), // Lowercase Case
                Arguments.of("COVINGTON", 1), // Uppercase Case
                Arguments.of("c", 11) // Partial Case

        );
    }

    @Test
    void find_SearchByState(){
        //Arrange
        StudentRequest request = StudentRequest.builder()
                .residentState("OH")
                .build();
        //Act
        List<StudentDto> actualList = studentRepository.find(request);
        //Assert
        assertEquals(25, actualList.size());
    }

    @ParameterizedTest
    @MethodSource("variousInputForUniversity")
    void find_SearchByUniversity(
            String university,
            int expectSize
    ){
        //Arrange
        StudentRequest request = StudentRequest.builder()
                .universityName(university)
                .build();
        //Act
        List<StudentDto> actualList = studentRepository.find(request);
        //Assert
        assertEquals(expectSize, actualList.size());

    }

    private static Stream<Arguments> variousInputForUniversity(){
        return Stream.of(
                Arguments.of("Northern Kentucky University", 4), //Normal Case
                Arguments.of("northern kentucky university", 4), // Lowercase Case
                Arguments.of("NORTHERN KENTUCKY UNIVERSITY", 4), // Uppercase Case
                Arguments.of("n", 33) // Partial Case

        );
    }

    @Test
    void find_SearchByGrade(){
        //Arrange
        StudentRequest request = StudentRequest.builder()
                .grade("Senior")
                .build();
        //Act
        List<StudentDto> actualList = studentRepository.find(request);
        //Assert
        assertEquals(8, actualList.size());

    }

    @Test
    void insertNewStudent_insertionSuccessful() {
        //Arrange
        StudentSignupRequest request = StudentSignupRequest.builder()
                .firstName("Joe")
                .lastName("Smith")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(1)
                .grade("Freshman")
                .major("General Studies")
                .email("test@email.com")
                .password("passw0rd!")
                .socialMediaLink(null)
                .build();
        int expectRowsAffected = 1;
        //Act
        int actualRowsAffected = studentRepository.insertNewStudent(request);
        //Assert
        assertEquals(expectRowsAffected, actualRowsAffected);
    }

    @Test
    void insertNewStudent_insertionFailed() {
        //Arrange
        StudentSignupRequest request = StudentSignupRequest.builder()
                .firstName("Joe")
                .lastName("Smith")
                .residentCity("Cincinnati")
                .residentState("OH")
                .universityId(null)
                .grade("Freshman")
                .major("General Studies")
                .email("test@email.com")
                .password("passw0rd!")
                .socialMediaLink(null)
                .build();
        //Act and Assert
        assertThrows(RuntimeException.class, ()-> studentRepository.insertNewStudent(request));
    }

}