package com.jonathansoriano.enterprisedevgroupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object for representing user information transferred between layers.
 * Contains user identification, role, and authentication credentials.
 *
 * @author [Author Name]
 * @version [Version]
 * @since [Release Version]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String role;
    private String email;
    private String password;
}
