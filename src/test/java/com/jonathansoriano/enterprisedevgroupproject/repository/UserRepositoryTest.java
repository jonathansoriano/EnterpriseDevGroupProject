package com.jonathansoriano.enterprisedevgroupproject.repository;

import com.jonathansoriano.enterprisedevgroupproject.domain.UserRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}