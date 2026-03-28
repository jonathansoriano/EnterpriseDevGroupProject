package com.jonathansoriano.enterprisedevgroupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//This DTO is used to return User info when searching for a particular user
// (Ex. GET endpoint "/student/profile", PUT endpoint "/student/profile")
public class UserDto {
    private Integer id;
    private String role;
    private String email;
    private String password;
}
