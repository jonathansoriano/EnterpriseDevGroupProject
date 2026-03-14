package com.jonathansoriano.enterprisedevgroupproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Data transfer object representing a user creation request.
 * Contains user role, email, and password for new user account creation.
 *
 * @author [Author Name]
 * @version [Version]
 * @since [Release Version]
 */
public class UserRequest {
    private String role;
    private String email;
    private String password;
}
