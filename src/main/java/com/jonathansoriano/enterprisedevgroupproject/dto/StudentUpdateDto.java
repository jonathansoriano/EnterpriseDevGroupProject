package com.jonathansoriano.enterprisedevgroupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//This DTO is used to return Student info when searching for a Particular student to update (PUT endpoint "/student/profile")
public class StudentUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String residentCity;
    private String residentState;
    private Integer universityId;
    private String grade;
    private String major;
    private String email;
    private String socialMediaLink;
}
