package com.jonathansoriano.enterprisedevgroupproject.model;

import com.jonathansoriano.enterprisedevgroupproject.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerUserDetailsTest {
    private UserDto userDto;
    private CustomerUserDetails userDetails;

    @BeforeEach
    void setUp() {
        UserDto userDto = UserDto.builder()
                .id(1)
                .role("USER")
                .email("test@email.com")
                .password("passw0rd!")
                .build();

        // Wrap it in your UserDetails implementation
        userDetails = new CustomerUserDetails(userDto);
    }

    @Test
    void shouldReturnEmailAsUsername() {
        assertEquals("test@email.com", userDetails.getUsername());
    }

    @Test
    void shouldReturnCorrectPassword() {
        assertEquals("passw0rd!", userDetails.getPassword());
    }

    @Test
    void shouldReturnEmptyAuthorities() {
        // Since your implementation currently returns Collections.emptyList()
        assertTrue(userDetails.getAuthorities().isEmpty());
    }

    @Test
    void shouldReturnTrueForAllAccountStatusMethods() {
        // Verifying the hardcoded 'true' values
        assertAll("Account Status",
                () -> assertTrue(userDetails.isAccountNonExpired()),
                () -> assertTrue(userDetails.isAccountNonLocked()),
                () -> assertTrue(userDetails.isCredentialsNonExpired()),
                () -> assertTrue(userDetails.isEnabled())
        );
    }
}