package com.jonathansoriano.enterprisedevgroupproject.repository;

import com.jonathansoriano.enterprisedevgroupproject.dto.UserDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    
    private static final String FIND_USER_BY_EMAIL = """
            SELECT id, role, email, password
            FROM app_user
            WHERE email = :email
            """;
    
    private final NamedParameterJdbcTemplate jdbcTemplate;
    
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public Optional<UserDto> findByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);
        
        try {
            UserDto user = jdbcTemplate.queryForObject(
                    FIND_USER_BY_EMAIL, 
                    params, 
                    new BeanPropertyRowMapper<>(UserDto.class, true)
            );
            return Optional.ofNullable(user);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
