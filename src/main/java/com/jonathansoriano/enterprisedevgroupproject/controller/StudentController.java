package com.jonathansoriano.enterprisedevgroupproject.controller;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
import com.jonathansoriano.enterprisedevgroupproject.model.Student;
import com.jonathansoriano.enterprisedevgroupproject.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {
    private final StudentService service;

    // Constructor Dependency Injection instead of Autowiring Service class
    public StudentController (StudentService service){
        this.service = service;
    }

    /**
     * Finds a list of students based on the provided search criteria.
     *
     * @param firstName       the first name of the student (optional)
     * @param lastName        the last name of the student (optional)
     * @param city            the city where the student resides (optional)
     * @param state           the state where the student resides (optional)
     * @param universityName  the name of the student's university (optional)
     * @param grade           the grade of the student (optional)
     * @return a {@code ResponseEntity} containing a list of students matching the search criteria
     */
    @GetMapping("findStudent")
    public ResponseEntity<List<Student>> find (
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false)String state,
            @RequestParam(required = false)String universityName,
            @RequestParam(required = false)String grade,
            @RequestParam(required = false)String major
    )
    {
        //We build our request by passing in the Query Params into this instance of StudentRequest
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

    @PostMapping("/studentSignUp")
    public ResponseEntity<String> createNewStudent(@RequestBody StudentSignupRequest student){

        String successfulInsertionMessage = service.insertNewStudent(student);

        return new ResponseEntity<>(successfulInsertionMessage , HttpStatus.CREATED);

    }
}
