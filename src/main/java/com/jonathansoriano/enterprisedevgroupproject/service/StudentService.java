package com.jonathansoriano.enterprisedevgroupproject.service;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentDto;
import com.jonathansoriano.enterprisedevgroupproject.exception.SearchNotFoundException;
import com.jonathansoriano.enterprisedevgroupproject.model.Student;
import com.jonathansoriano.enterprisedevgroupproject.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public List<Student> find(StudentRequest request){
        List<Student> students = buildStudentListFromDtoList(repository.find(request));

        if(CollectionUtils.isEmpty(students)){
            throw new SearchNotFoundException("Student Not found!");
        }
        return students;

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
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .residentCity(dto.getResidentCity())
                .residentState(dto.getResidentState())
                .universityName(dto.getUniversityName())
                .grade(dto.getGrade())
                .email(dto.getEmail())
                .socialMediaLink(dto.getSocialMediaLink())
                .build();
    }
}
