package com.jonathansoriano.enterprisedevgroupproject.repository;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.UserRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentAccountDetailsDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentUpdateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
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

    @Test
    void findByEmail_validEmail_Successful(){
        //Arrange
        String validEmail = "sarah.johnson@mail.uc.edu";

        StudentAccountDetailsDto expectedStudentAccountDetailsDto = StudentAccountDetailsDto.builder()
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



        //Act
        Optional<StudentAccountDetailsDto> actualStudentAccountDetailsDto = studentRepository.findByEmail(validEmail);
        //Assert

        assertEquals(Optional.of(expectedStudentAccountDetailsDto), actualStudentAccountDetailsDto);
    }

    @Test
    void findByEmail_invalidEmail_Unsuccessful(){
        //Arrange
        String invalidEmail = "sarah.johnson";

        //Act
        Optional<StudentAccountDetailsDto> actualStudentAccountDetailsDto = studentRepository.findByEmail(invalidEmail);
        //Assert
        assertEquals(Optional.empty(), actualStudentAccountDetailsDto);
    }

    @Test
    void findByEmail_nonExistingEmail_Unsuccessful(){
        //Arrange
        String nonExistingEmail = "yuno.miles@uc.mail.edu";

        //Act
        Optional<StudentAccountDetailsDto> actualStudentAccountDetailsDto = studentRepository.findByEmail(nonExistingEmail);

        //Assert
        assertEquals(Optional.empty(), actualStudentAccountDetailsDto);
    }

    @Test
    void findByEmail_nullEmail_Unsuccessful(){

        //Act & Assert

        //When a null value gets to the findByEmail method, our SqlUtils.andAddCondition() method checks if the value's a null
        // If it is a null, the method returns an empty String. When an empty string is returned to the StringBuilder, it won't add
        //the AND_EMAIL part of the SQL query, and instead will execute a general query (SELECT_VERSION_UNIVERSITY_NAME)
        // and return multiple rows - hence the IncorrectResultSizeDataAccessExcemption

        assertThrows(IncorrectResultSizeDataAccessException.class, ()-> studentRepository.findByEmail(null));
    }

    @Test
    void findStudentByEmail_validEmail_Successful(){
        //Arrange
        String validEmail = "michael.chen@mail.uc.edu";

        StudentUpdateDto expectedStudentUpdateDto = StudentUpdateDto.builder()
                .id(2L)
                .firstName("Michael")
                .lastName("Chen")
                .residentCity("Mason")
                .residentState("OH")
                .universityId(1)
                .grade("Senior")
                .major("Electrical Engineering")
                .email("michael.chen@mail.uc.edu")
                .socialMediaLink("https://twitter.com/mchen_uc")
                .build();
        //Act
        Optional<StudentUpdateDto> actualStudentUpdateDto = studentRepository.findStudentByEmail(validEmail);
        //Assert
        assertEquals(Optional.of(expectedStudentUpdateDto), actualStudentUpdateDto);
    }

    @Test
    void findStudentByEmail_invalidEmail_Unsuccessful(){
        //Arrange
        String invalidEmail = "michael.chen";

        //Act
        Optional<StudentUpdateDto> actualStudentUpdateDto = studentRepository.findStudentByEmail(invalidEmail);

        //Assert

        assertEquals(Optional.empty(), actualStudentUpdateDto);
    }

    @Test
    void findStudentByEmail_nullEmail_Unsuccessful(){
        //Act & Assert
        assertThrows(IncorrectResultSizeDataAccessException.class, ()-> studentRepository.findStudentByEmail(null));
    }

    @Test
    void updateStudent_NonNullFields_Successful(){
        //Arrange
        StudentUpdateDto studentUpdateDto = StudentUpdateDto.builder()
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

        int expectedStudentResult = 1;

        //Act
        int actualStudentResult = studentRepository.updateStudent(studentUpdateDto);

        //Assert
        assertEquals(expectedStudentResult, actualStudentResult);
    }

    @Test
    void updateStudent_ValidNullField_Successful(){
        //Arrange (Social Media Link is the only column that can be null, the rest of the fields are NOT NULL)
        StudentUpdateDto student = StudentUpdateDto.builder()
                .id(3L)
                .firstName("Emily")
                .lastName("Rodriguez")
                .residentCity("Blue Ash")
                .residentState("OH")
                .universityId(1)
                .grade("Freshman")
                .major("Biology")
                .email("emily.rodriguez@mail.uc.edu")
                .socialMediaLink(null)
                .build();

        int expectedStudentResult = 1;

        //Act
        int actualStudentResult = studentRepository.updateStudent(student);

        //Assert
        assertEquals(expectedStudentResult, actualStudentResult);
    }

    @Test
    void updateStudent_requiredFieldsNullUnsuccessful(){
        //Arrange
        StudentUpdateDto invalidStudent = StudentUpdateDto.builder()
                .id(1L)
                .firstName(null)
                .lastName(null)
                .residentCity(null)
                .residentState(null)
                .universityId(null)
                .grade(null)
                .major(null)
                .email("sarah.johnson@mail.uc.edu")
                .socialMediaLink("https://linkedin.com/in/sarahjohnson")
                .build();


        //Act & Assert

        //When you execute an update where a column is defined as NOT NULL in your database schema,
        // but your SQL provides a null value, the database (e.g., MySQL, PostgreSQL, H2) will reject the operation.
        assertThrows(DataIntegrityViolationException.class, ()-> studentRepository.updateStudent(invalidStudent));

    }

}