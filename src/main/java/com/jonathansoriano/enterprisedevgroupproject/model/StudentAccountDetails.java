package com.jonathansoriano.enterprisedevgroupproject.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//This class is used to display the Student account details of the currently authenticated user to the UI
public class StudentAccountDetails {
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
