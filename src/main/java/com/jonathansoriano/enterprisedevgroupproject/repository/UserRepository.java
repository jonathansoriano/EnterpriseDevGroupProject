package com.jonathansoriano.enterprisedevgroupproject.repository;

import com.jonathansoriano.enterprisedevgroupproject.domain.UserRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.UserDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    
    private static final String FIND_USER_BY_EMAIL = """
            SELECT id, role, email, password
            FROM app_user
            WHERE 1 = 1 AND email = :email
            """;

    public static final String INSERT_NEW_APP_USER = """
            INSERT INTO app_user (role, email, password)
            VALUES (:role, :email, :password)
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

    /**
     * Inserts a new user record into the database, specifically inserts a new user
     * into the app_user table, using the details provided in the
     * {@link UserRequest} object.
     * The method utilizes the specified role, email, and password from the request
     * object to perform the insertion.
     *
     * @param userRequest the {@link UserRequest} object containing the user's role,
     *                    email, and password.
     * @return an integer indicating the number of rows affected by the insert
     *         operation. A value greater
     *         than 0 indicates that the operation was successful
     */
    public int insertNewUser(UserRequest userRequest) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("role", userRequest.getRole())
                .addValue("email", userRequest.getEmail())
                .addValue("password", userRequest.getPassword());
        try {
            return jdbcTemplate.update(INSERT_NEW_APP_USER, params);
        } catch (Exception ex) {
            throw new RuntimeException("User insertion failed due to a database error", ex);
        }
    }
}
