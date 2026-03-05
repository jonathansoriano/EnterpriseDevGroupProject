package com.jonathansoriano.enterprisedevgroupproject.service;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.UserRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentDto;
import com.jonathansoriano.enterprisedevgroupproject.exception.SearchNotFoundException;
import com.jonathansoriano.enterprisedevgroupproject.model.Student;
import com.jonathansoriano.enterprisedevgroupproject.repository.StudentRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // We instantiate it here once in the constructor, so we don't have to instantiate it every time we use it inside this class.
    }

    /**
     * Searches for students based on the given request criteria and returns a list of matching students.
     * If no students are found, a {@link SearchNotFoundException} is thrown.
     *
     * @param request the {@link StudentRequest} object containing the search parameters for retrieving students.
     * @return a list of {@link Student} objects that match the search criteria.
     * @throws SearchNotFoundException if no students are found matching the given criteria.
     */
    public List<Student> find(StudentRequest request){
        List<Student> students = buildStudentListFromDtoList(repository.find(request));

        if(CollectionUtils.isEmpty(students)){
            throw new SearchNotFoundException("Student Not found!");
        }
        return students;

    }

    /**
     * Inserts a new student into the system by creating corresponding entries in the user table
     * and the student table. The student's password is hashed before insertion for security purposes.
     *
     * @param student The {@link StudentSignupRequest} object containing the new student's details
     *                such as name, email, resident information, university details, and password.
     * @return A message indicating the success or failure of the student signup operation.
     *         If successful, returns "Student Signup Successful!".
     *         Otherwise, an exception is thrown.
     * @throws RuntimeException if the insertion into the student table or user table fails.
     *                          Specific exceptions for these failures could be implemented in the future.
     */
    public String insertNewStudent(StudentSignupRequest student){
        //Logic to Hash the plan password before inserting into the app_user Table
        String hashedPassword = hashPlainTextPassword(student.getPassword());
        //Create a new UserRequest object from the StudentSignupRequest object to be able to insert into the app_user Table
        UserRequest userRequest = buildUserRequestFromStudentSignupRequest(student, hashedPassword);
        //Logic to add a new user(student user) to the User Table (after testing try figuring out how to use a CompletableFuture for async performance)
        int userInsertionResult = repository.insertNewUser(userRequest);


        //Logic to add a new student to the Student Table
        int studentInsertionResult = repository.insertNewStudent(student);

        return "Student Signup Successful!";
    }

    private String hashPlainTextPassword(String password){
        return passwordEncoder.encode(password);
    }

    /**
     * Converts a {@link StudentSignupRequest} object into a {@link UserRequest} object.
     * This method is used to prepare a user request for inserting a user into the system's user table.
     * The resulting {@link UserRequest} includes the user's email, hashed password, and a default role of "USER".
     *
     * @param studentSignupRequest the source {@link StudentSignupRequest} containing the student's signup details,
     *                             such as email and plain text password.
     * @param hashedPassword       the hashed version of the student's plain text password to ensure security.
     * @return a {@link UserRequest} object containing the mapped user data.
     */
    private static UserRequest buildUserRequestFromStudentSignupRequest(StudentSignupRequest studentSignupRequest, String hashedPassword){
        return UserRequest.builder()
                .role("USER")
                .email(studentSignupRequest.getEmail())
                .password(hashedPassword)
                .build();
    }

    /**
     * Converts a list of StudentDto objects into a list of Student objects.
     * Each StudentDto in the input list is transformed into a corresponding Student
     * instance by invoking the buildStudentFromDto method.
     *
     * @param dtoList the list of StudentDto objects to be converted into Student objects.
     * @return a list of Student objects created from the given list of StudentDto objects.
     */
    static List<Student> buildStudentListFromDtoList(List<StudentDto> dtoList){
        List<Student> studentList = new ArrayList<>();

        for (StudentDto dto : dtoList){
            studentList.add(buildStudentFromDto(dto));
        }
        return studentList;
    }

    /**
     * Builds a {@link Student} object from a given {@link StudentDto}.
     * The method maps the fields from the provided StudentDto to a new Student instance.
     *
     * @param dto the {@link StudentDto} containing student data to be converted into a {@link Student}.
     * @return a new {@link Student} object populated with the data from the provided {@link StudentDto}.
     */
    static Student buildStudentFromDto(StudentDto dto){
        return Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .residentCity(dto.getResidentCity())
                .residentState(dto.getResidentState())
                .universityName(dto.getUniversityName())
                .grade(dto.getGrade())
                .major(dto.getMajor())
                .email(dto.getEmail())
                .socialMediaLink(dto.getSocialMediaLink())
                .build();
    }
}
