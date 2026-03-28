package com.jonathansoriano.enterprisedevgroupproject.repository;

import com.jonathansoriano.enterprisedevgroupproject.domain.UserRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.UserDto;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public static final String UPDATE_APP_USER = """
            UPDATE app_user
            SET email = :email, password = :password
            WHERE id = :id
            """;
    
    private final NamedParameterJdbcTemplate jdbcTemplate;
    
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a user from the database based on their email address.
     *
     * @param email the email address of the user to be retrieved.
     * @return an {@link Optional} containing the {@link UserDto} if a user with the specified email exists.
     *         If no user is found, an empty {@link Optional} is returned when EmptyResultDataAccessException is thrown.
     */
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
        } catch (EmptyResultDataAccessException ex) {
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

    /**
     * Updates an existing user's information in the database.
     * This method updates the user's details such as email and password based on the provided {@link UserDto}.
     *
     * @param updatedUser the {@link UserDto} object containing the updated information for the user,
     *                    including their ID, email, and password.
     * @return an integer indicating the number of rows affected by the update operation.
     *         A value greater than 0 indicates a successful update.
     * @throws RuntimeException if the update operation fails due to a database error.
     */
    public int updateUser(UserDto updatedUser) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", updatedUser.getId())
                .addValue("email", updatedUser.getEmail())
                .addValue("password", updatedUser.getPassword());

        StringBuilder sql = new StringBuilder(UPDATE_APP_USER);

        try {
            return jdbcTemplate.update(sql.toString(), params);
        }catch (Exception ex) {
            throw new RuntimeException("User insertion failed due to a database error", ex);
        }
    }
}
