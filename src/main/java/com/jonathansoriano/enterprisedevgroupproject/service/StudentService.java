package com.jonathansoriano.enterprisedevgroupproject.service;

import com.jonathansoriano.enterprisedevgroupproject.domain.EditStudentDetailsRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.UserRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentAccountDetailsDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentUpdateDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.UserDto;
import com.jonathansoriano.enterprisedevgroupproject.exception.SearchNotFoundException;
import com.jonathansoriano.enterprisedevgroupproject.model.Student;
import com.jonathansoriano.enterprisedevgroupproject.model.StudentAccountDetails;
import com.jonathansoriano.enterprisedevgroupproject.repository.StudentRepository;
import com.jonathansoriano.enterprisedevgroupproject.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Searches for students based on the given request criteria and returns a list
     * of matching students.
     * If no students are found, a {@link SearchNotFoundException} is thrown.
     *
     * @param request the {@link StudentRequest} object containing the search
     *                parameters for retrieving students.
     * @return a list of {@link Student} objects that match the search criteria.
     * @throws SearchNotFoundException if no students are found matching the given
     *                                 criteria.
     */
    @Cacheable(value = "students")
    public List<Student> find(StudentRequest request) {
        List<Student> students = buildStudentListFromDtoList(studentRepository.find(request));

        if (CollectionUtils.isEmpty(students)) {
            throw new SearchNotFoundException("Student Not found!");
        }
        return students;

    }

    /**
     * Finds the student account details by the provided email.
     *
     * @param usersUsername the email of the user to search for
     * @return the student account details associated with the given email
     * @throws SearchNotFoundException if no student is found with the given email
     */
    public StudentAccountDetails findByEmail(String usersUsername) {
        StudentAccountDetailsDto studentDto = studentRepository.findByEmail(usersUsername)
                .orElseThrow(()-> new SearchNotFoundException("Student account not found with email: " +  usersUsername));

        return buildStudentAccountDetailFromDto(studentDto, usersUsername);
    }

    /**
     * Inserts a new student into the system by creating corresponding entries in
     * the user table
     * and the student table. The student's password is hashed before insertion for
     * security purposes. Transactional annotation is used to ensure both writes
     * either succeed together or both roll back together. Without it, a failure on
     * the second insert (student) after the first insert (user) succeeded would leave
     * the database in an inconsistent state a user account with no matching student profile.
     *
     * @param student The {@link StudentSignupRequest} object containing the new
     *                student's details
     *                such as name, email, resident information, university details,
     *                and password.
     * @return A message indicating the success or failure of the student signup
     *         operation.
     *         If successful, returns "Student Signup Successful!".
     *         Otherwise, an exception is thrown.
     * @throws RuntimeException if the insertion into the student table or user
     *                          table fails.
     *                          Specific exceptions for these failures could be
     *                          implemented in the future.
     */
    @Transactional
    public String insertNewStudent(StudentSignupRequest student) {
        // Step 1: Hash the plain-text password before storing it in the app_user table
        String hashedPassword = hashPlainTextPassword(student.getPassword());

        // Step 2: Build a UserRequest DTO from the signup request to insert into
        // app_user table
        UserRequest userRequest = buildUserRequestFromStudentSignupRequest(student, hashedPassword);

        // Step 3: Insert the user credentials into the app_user table first
        int userInsertionResult = userRepository.insertNewUser(userRequest);

        // Step 4: Insert the student profile into the student table
        int studentInsertionResult = studentRepository.insertNewStudent(student);

        return "Student Signup Successful!";
    }

    @Transactional
    public String updateStudent(String username, EditStudentDetailsRequest studentDetails) {
        //Do a find in the Student table using the username (email) and assign returned Student from repo to Student object
        StudentUpdateDto updatedStudent = studentRepository.findStudentByEmail(username).orElseThrow(()-> new SearchNotFoundException("Student Not found!"));
        //Do a find in the User table using the username (email) and assign returned User from repo to User Object
        UserDto updatedUser = userRepository.findByEmail(username).orElseThrow(()-> new SearchNotFoundException("User not found!"));
        //Set the values of the Student object with the values in the EditStudentDetailsRequest object
        updatedStudent.setFirstName(studentDetails.getFirstName());
        updatedStudent.setLastName(studentDetails.getLastName());
        updatedStudent.setEmail(studentDetails.getEmail());
        updatedStudent.setResidentCity(studentDetails.getResidentCity());
        updatedStudent.setResidentState(studentDetails.getResidentState());
        updatedStudent.setUniversityId(studentDetails.getUniversityId());
        updatedStudent.setGrade(studentDetails.getGrade());
        updatedStudent.setMajor(studentDetails.getMajor());
        updatedStudent.setEmail(studentDetails.getEmail());
        updatedStudent.setSocialMediaLink(studentDetails.getSocialMediaLink());
        //Set the values of the User object with the values in the EditStudentDetailsRequest object
        updatedUser.setEmail(studentDetails.getEmail());
        //Password check to make sure we aren't setting the password to an empty string
        if (!studentDetails.getPassword().isBlank()) {
            updatedUser.setPassword(passwordEncoder.encode(studentDetails.getPassword()));
        }
        //Send Updated Student Object to the Repository layer and wait to see if the update was successful
        Integer studentResult = studentRepository.updateStudent(updatedStudent);
        Integer userResult = userRepository.updateUser(updatedUser);

        return "Account Updated Successfully!";
    }

    private String hashPlainTextPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Converts a {@link StudentSignupRequest} object into a {@link UserRequest}
     * object.
     * This method is used to prepare a user request for inserting a user into the
     * system's user table.
     * The resulting {@link UserRequest} includes the user's email, hashed password,
     * and a default role of "USER".
     *
     * @param studentSignupRequest the source {@link StudentSignupRequest}
     *                             containing the student's signup details,
     *                             such as email and plain text password.
     * @param hashedPassword       the hashed version of the student's plain text
     *                             password to ensure security.
     * @return a {@link UserRequest} object containing the mapped user data.
     */
    private static UserRequest buildUserRequestFromStudentSignupRequest(StudentSignupRequest studentSignupRequest,
            String hashedPassword) {
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
     * @param dtoList the list of StudentDto objects to be converted into Student
     *                objects.
     * @return a list of Student objects created from the given list of StudentDto
     *         objects.
     */
    static List<Student> buildStudentListFromDtoList(List<StudentDto> dtoList) {
        List<Student> studentList = new ArrayList<>();

        for (StudentDto dto : dtoList) {
            studentList.add(buildStudentFromDto(dto));
        }
        return studentList;
    }

    /**
     * Builds a {@link Student} object from a given {@link StudentDto}.
     * The method maps the fields from the provided StudentDto to a new Student
     * instance.
     *
     * @param dto the {@link StudentDto} containing student data to be converted
     *            into a {@link Student}.
     * @return a new {@link Student} object populated with the data from the
     *         provided {@link StudentDto}.
     */
    static Student buildStudentFromDto(StudentDto dto) {
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

    static StudentAccountDetails buildStudentAccountDetailFromDto(StudentAccountDetailsDto studentDto, String email) {

        return StudentAccountDetails.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .residentCity(studentDto.getResidentCity())
                .residentState(studentDto.getResidentState())
                .universityName(studentDto.getUniversityName())// should we return the ID or name of university? how will it handled in front end?
                .grade(studentDto.getGrade())
                .major(studentDto.getMajor())
                .email(email)
                .socialMediaLink(studentDto.getSocialMediaLink())
                .build();
    }

}
