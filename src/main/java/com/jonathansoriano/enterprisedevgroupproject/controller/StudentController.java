package com.jonathansoriano.enterprisedevgroupproject.controller;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
import com.jonathansoriano.enterprisedevgroupproject.model.CustomerUserDetails;
import com.jonathansoriano.enterprisedevgroupproject.model.Student;
import com.jonathansoriano.enterprisedevgroupproject.model.StudentAccountDetails;
import com.jonathansoriano.enterprisedevgroupproject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// The @RestController annotation combines @Controller and @ResponseBody, meaning every
// method return value is automatically serialized as JSON in the HTTP response body.
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    // Constructor Dependency Injection instead of Autowiring Service class
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
     * @return a {@code ResponseEntity} containing a list of students matching the
     *         search criteria
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
        // We build our request by passing in the Query Params into this instance of
        // StudentRequest
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
     * Retrieves the profile information of the currently authenticated student.
     *
     * @param userDetails the authenticated user's details, containing information about the current user
     * @return a {@code ResponseEntity} containing the student's account details
     */
    @GetMapping("/profile")
    public ResponseEntity<StudentAccountDetails> getProfile(@AuthenticationPrincipal CustomerUserDetails userDetails) {
        // 'userDetails' IS the Principal.
        String currentUserName = userDetails.getUsername();

        StudentAccountDetails studentAccountDetails = service.findByEmail(currentUserName);
        return ResponseEntity.ok(studentAccountDetails);
    }

    /**
     * Creates a new student in the system based on the provided signup request.
     *
     * @param student the {@code StudentSignupRequest} object containing the
     *                student's information
     *                such as first name, last name, city, state, university ID,
     *                grade, major, email,
     *                password, and social media link
     * @return a {@code ResponseEntity} containing a success message upon successful
     *         student creation
     */

    @PostMapping
    public ResponseEntity<String> createNewStudent(@Valid @RequestBody StudentSignupRequest student) {

        String successfulInsertionMessage = service.insertNewStudent(student);

        return new ResponseEntity<>(successfulInsertionMessage, HttpStatus.CREATED);

    }

    @PutMapping("/account")
    public ResponseEntity<String> updateStudent(@Valid @RequestBody StudentSignupRequest student) {
        //String successfulAccountUpdate = service.updateStudent(student);

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}