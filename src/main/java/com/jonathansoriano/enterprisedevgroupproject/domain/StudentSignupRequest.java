package com.jonathansoriano.enterprisedevgroupproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object representing a student signup request with registration details.
 * Encapsulates student profile information and authentication credentials for account creation.
 *
 * @author [Author Name]
 * @version [Version]
 * @since [Release Version]
 */
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
