package com.jonathansoriano.enterprisedevgroupproject.repository;

import com.jonathansoriano.enterprisedevgroupproject.domain.UserRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentUpdateDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_success() {
        //Arrange
        String expectedEmail = "sarah.johnson@mail.uc.edu";
        //Act
        Optional<UserDto> user = userRepository.findByEmail(expectedEmail);
        //Assert
        assertEquals(expectedEmail, user.get().getEmail());
    }

    @Test
    void findByEmail_failure() {
        // Arrange
        String nonExistingEmail = "test@mail.edu";

        // Act
        Optional<UserDto> user = userRepository.findByEmail(nonExistingEmail);

        // Assert
        //Check if the Optional is empty
        assertTrue(user.isEmpty(), "The user should not have been found");
    }

    @Test
    void insertNewUser_insertionSuccessful() {
        //Arrange
        UserRequest userRequest = UserRequest.builder()
                .role("USER")
                .email("test@email.com")
                .password("$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy")
                .build();
        int expectRowsAffected = 1;
        //Act
        int actualRowsAffected = userRepository.insertNewUser(userRequest);
        //Assert
        assertEquals(expectRowsAffected, actualRowsAffected);
    }

    @Test
    void insertNewUser_insertionFailed() {
        //Arrange
        UserRequest request = UserRequest.builder()
                .role("User")
                .email("test@email.com")
                .password(null)
                .build();
        //Assert
        assertThrows(RuntimeException.class, ()-> userRepository.insertNewUser(request));
    }

    @Test
    void updateUser_NonNullFields_Successful(){
        //Arrange
        UserDto UserDto = com.jonathansoriano.enterprisedevgroupproject.dto.UserDto.builder()
                .id(1L)
                .role("USER")
                .email("sarah.johnson@mail.uc.edu")
                .password("$2a$10$cT37ge3YHk2NxIjDvUpns.CucoBA8cQ.DzJXoqcIVJ6nQUZpB9SVa")
                .build();

        int expectedUserResult = 1;

        //Act
        int actualUserResult = userRepository.updateUser(UserDto);

        //Assert
        assertEquals(expectedUserResult, actualUserResult);
    }


    @Test
    void updateUser_requiredFieldsNullUnsuccessful(){
        //Arrange
        UserDto invalidUser = UserDto.builder()
                .id(1L)
                .role(null)
                .email(null)
                .password(null)
                .build();


        //Act & Assert

        //When you execute an update where a column is defined as NOT NULL in your database schema,
        // but your SQL provides a null value, the database (e.g., MySQL, PostgreSQL, H2) will reject the operation.
        assertThrows(DataIntegrityViolationException.class, ()-> userRepository.updateUser(invalidUser));

    }

    @Test
    void updateUser_NonExistingId_Unsuccessful(){
        //Arrange (User ID 1000 doesn't exist in the database to when trying to update the User
        //WHERE ID = 1000, it will execute the query but no rows are affected.
        UserDto invalidUser = UserDto.builder()
                .id(1000L)
                .role("USER")
                .email("sarah.johnson@mail.uc.edu")
                .password("$2a$10$cT37ge3YHk2NxIjDvUpns.CucoBA8cQ.DzJXoqcIVJ6nQUZpB9SVa")
                .build();

        int expectedUserResult = 0;

        //Act
        int actualUserResult = userRepository.updateUser(invalidUser);

        //Assert
        assertEquals(expectedUserResult, actualUserResult);
    }
}