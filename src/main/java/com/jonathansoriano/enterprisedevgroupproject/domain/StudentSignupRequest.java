package com.jonathansoriano.enterprisedevgroupproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentSignupRequest {
    private String firstName;
    private String lastName;
    private String residentCity;
    private String residentState;
    private Integer universityId;
    private String grade;
    private String major;
    private String email;
    private String password;
    private String socialMediaLink;
}
