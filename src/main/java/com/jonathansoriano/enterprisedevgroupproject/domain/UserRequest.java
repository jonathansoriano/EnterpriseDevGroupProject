package com.jonathansoriano.enterprisedevgroupproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//This is the class that will be used to create a new user in the app_user table.
public class UserRequest {
    private String role;
    private String email;
    private String password;
}
