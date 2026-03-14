package com.jonathansoriano.enterprisedevgroupproject.controller;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
import com.jonathansoriano.enterprisedevgroupproject.model.Student;
import com.jonathansoriano.enterprisedevgroupproject.service.StudentService;
import jakarta.validation.Valid; // Use javax.validation.Valid if on Spring Boot 2.x
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    /**
     * Finds a list of students based on the provided search criteria.
     *
     * @param firstName      the first name of the student (optional)
     * @param lastName       the last name of the student (optional)
     * @param city           the city where the student resides (optional)
     * @param state          the state where the student resides (optional)
     * @param universityName the name of the student's university (optional)
     * @param grade          the grade of the student (optional)
     * @param major          the major of the student (optional)
     * @return a {@code ResponseEntity} containing a list of students matching the search criteria
     */
    @GetMapping
    public ResponseEntity<List<Student>> find(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String universityName,
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) String major) {

        log.debug("Received request to find students with search criteria");

        StudentRequest request = StudentRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .residentCity(city)
                .residentState(state)
                .universityName(universityName)
                .grade(grade)
                .major(major)
                .build();

        return ResponseEntity.ok(service.find(request));
    }

    /**
     * Creates a new student in the system based on the provided signup request.
     *
     * @param student the {@code StudentSignupRequest} object containing the student's information
     * @return a {@code ResponseEntity} containing a success message upon successful student creation
     */
    @PostMapping
    public ResponseEntity<String> createNewStudent(@Valid @RequestBody StudentSignupRequest student) {
        
        log.info("Received request to create a new student");
        
        String successfulInsertionMessage = service.insertNewStudent(student);
        return new ResponseEntity<>(successfulInsertionMessage, HttpStatus.CREATED);
    }
}