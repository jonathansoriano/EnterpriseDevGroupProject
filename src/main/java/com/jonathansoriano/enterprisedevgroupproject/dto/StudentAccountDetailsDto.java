package com.jonathansoriano.enterprisedevgroupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentAccountDetailsDto {
    private String id;
    private String firstName;
    private String lastName;
    private String residentCity;
    private String residentState;
    private String universityName;
    private String grade;
    private String major;
    private String email;
    private String socialMediaLink;
}
