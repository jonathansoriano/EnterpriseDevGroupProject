package com.jonathansoriano.enterprisedevgroupproject.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentSignupRequest {
    // CHANGE NOTE (Rohit Vijai, 2026-03-15): Added Jakarta Bean Validation annotations (@NotBlank, @NotNull, @Email, @Size, @Pattern) to every field so malformed signup payloads are rejected with HTTP 400 before reaching the service layer.
    @NotBlank(message = "First name is required")
    @Size(max = 20, message = "First name must be 20 characters or fewer")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 20, message = "Last name must be 20 characters or fewer")
    private String lastName;

    @NotBlank(message = "Resident city is required")
    @Size(max = 40, message = "Resident city must be 40 characters or fewer")
    private String residentCity;

    @NotBlank(message = "Resident state is required")
    @Size(min = 2, max = 2, message = "Resident state must be a 2-letter code")
    @Pattern(regexp = "^[A-Za-z]{2}$", message = "Resident state must contain only letters")
    private String residentState;

    @NotNull(message = "University ID is required")
    private Integer universityId;

    @NotBlank(message = "Grade is required")
    @Size(max = 10, message = "Grade must be 10 characters or fewer")
    private String grade;

    @NotBlank(message = "Major is required")
    @Size(max = 255, message = "Major must be 255 characters or fewer")
    private String major;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid format")
    @Size(max = 255, message = "Email must be 255 characters or fewer")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password;

    @Size(max = 255, message = "Social media link must be 255 characters or fewer")
    private String socialMediaLink;
}
