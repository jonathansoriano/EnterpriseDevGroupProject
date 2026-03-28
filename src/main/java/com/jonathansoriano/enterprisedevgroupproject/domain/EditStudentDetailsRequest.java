package com.jonathansoriano.enterprisedevgroupproject.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditStudentDetailsRequest {
    @NotBlank(message = "First name field is required")
    private String firstName;

    @NotBlank(message = "Last name field is required")
    private String lastName;

    @NotBlank(message = "Resident City field is required")
    private String residentCity;

    @NotBlank(message = "Resident State field is required")
    @Size(min = 2, max = 2, message = "Resident state must be a Capitalized 2-letter code")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Resident state field must contain 2 capitalized letters")
    private String residentState;

    @NotNull(message = "University ID is required")
    private Integer universityId;

    @NotBlank(message = "Grade field is required")
    private String grade;

    @NotBlank(message = "Major field is required")
    private String major;

    @NotBlank(message = "Email field is required")
    @Email(message = "Please provide a valid email address")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email format is invalid"
    )
    private String email;

    @Pattern(
            regexp = "^$|^.{4,20}$",
            message = "Password must be between 4 and 20 characters long or left blank"
    )
    private String password;

    private String socialMediaLink;
}
