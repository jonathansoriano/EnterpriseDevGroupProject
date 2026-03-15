package com.jonathansoriano.enterprisedevgroupproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentSignupRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String residentCity;
    private String residentState;
    private Integer universityId;
    private String grade;
    private String major;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String socialMediaLink;
}
