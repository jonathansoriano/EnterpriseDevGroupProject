package com.jonathansoriano.enterprisedevgroupproject.service;

import com.jonathansoriano.enterprisedevgroupproject.dto.UserDto;
import com.jonathansoriano.enterprisedevgroupproject.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        return User.builder()
                .username(userDto.getEmail())
                .password(userDto.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userDto.getRole())))
                .build();
    }
}
