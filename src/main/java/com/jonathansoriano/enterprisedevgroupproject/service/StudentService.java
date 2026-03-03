package com.jonathansoriano.enterprisedevgroupproject.service;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
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
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Student> find(StudentRequest request){
        List<Student> students = buildStudentListFromDtoList(repository.find(request));

        if(CollectionUtils.isEmpty(students)){
            throw new SearchNotFoundException("Student Not found!");
        }
        return students;

    }

    public String insertNewStudent(StudentSignupRequest student){
        
        //Logic to Hash the password and replace password field with hashed password for student before inserting into User Table

        //Logic to add a new student to the User Table

        //Logic to add a new student to the Student Table
        int studentInsertionResult = repository.insertNewStudent(student);

        if (studentInsertionResult == 0){
            //Temporary Exception Handling, need to create Custom Exception for when Student Creation fails
            throw new RuntimeException("Student Signup Failed!");
        }
        return "Student Signup Successful!";
    }

    static List<Student> buildStudentListFromDtoList(List<StudentDto> dtoList){
        List<Student> studentList = new ArrayList<>();

        for (StudentDto dto : dtoList){
            studentList.add(buildStudentFromDto(dto));
        }
        return studentList;
    }

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
