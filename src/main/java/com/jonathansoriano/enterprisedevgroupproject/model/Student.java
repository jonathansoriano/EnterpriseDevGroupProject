package com.jonathansoriano.enterprisedevgroupproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Model class representing a student entity with profile and academic information.
 * Used to display and manage student information throughout the application.
 *
 * @author [Author Name]
 * @version [Version]
 * @since [Release Version]
 */
public class Student {
    //Got rid of studentID
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
