package com.jonathansoriano.enterprisedevgroupproject.repository;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.domain.StudentSignupRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentAccountDetailsDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentDto;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentUpdateDto;
import com.jonathansoriano.enterprisedevgroupproject.util.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class StudentRepository {
    // Alias columns to match StudentDto properties (camelCase), otherwise you'll
    // get an exception due to mapping issues
    public static final String SELECT_VERSION_UNIVERSITY_NAME = """
            SELECT
              s.id AS id,
              s.first_name AS firstName,
              s.last_name AS lastName,
              s.resident_city AS residentCity,
              s.resident_state AS residentState,
              u.name AS universityName,
              s.grade AS grade,
              s.major AS major,
              s.email AS email,
              s.social_media_link AS socialMediaLink
            FROM student s
            JOIN university u ON s.university_id = u.id
            WHERE 1 = 1
            """;

    public static final String SELECT_VERSION_UNIVERSITY_ID = """
            SELECT
              s.id AS id,
              s.first_name AS firstName,
              s.last_name AS lastName,
              s.resident_city AS residentCity,
              s.resident_state AS residentState,
              s.university_id AS universityId,
              s.grade AS grade,
              s.major AS major,
              s.email AS email,
              s.social_media_link AS socialMediaLink
            FROM student s
            WHERE 1 = 1
            """;
    public static final String AND_FIRSTNAME = "AND LOWER(s.first_name) LIKE :firstName";
    public static final String AND_LASTNAME = "AND LOWER(s.last_name) LIKE :lastName";
    public static final String AND_RESIDENT_CITY = "AND LOWER(s.resident_city) LIKE :residentCity";
    public static final String AND_RESIDENT_STATE = "AND s.resident_state = :residentState";
    public static final String AND_UNIVERSITY_NAME = "AND LOWER(u.name) LIKE :universityName";
    public static final String AND_GRADE = "AND s.grade = :grade";
    public static final String AND_MAJOR = "AND LOWER(s.major) LIKE :major";

    public static final String AND_EMAIL = "AND s.email = :email";




    public static final String INSERT_NEW_STUDENT = """
            INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major,email, social_media_link)
            VALUES (:firstName, :lastName, :residentCity, :residentState, :universityId, :grade, :major, :email, :socialMediaLink)
            """;

    public static final String UPDATE_STUDENT_INFO = """
            UPDATE student
            SET first_name = :firstName, last_name = :lastName, resident_city = :residentCity, resident_state = :residentState, university_id = :universityId, grade = :grade, major = :major,email = :email, social_media_link = :socialMediaLink
            WHERE id = :id
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StudentRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a list of students matching the criteria specified in the given
     * request object.
     * The query is dynamically built based on the non-null fields in the request.
     *
     * @param request the {@link StudentRequest} object containing the search
     *                criteria such as
     *                first name, last name, resident city, resident state,
     *                university name,
     *                grade, and major.
     * @return a {@link List} of {@link StudentDto} objects that match the specified
     *         criteria.
     */
    public List<StudentDto> find(StudentRequest request) {
        // MapSqlParameterSource allows you to use placeholders in Query build and
        // assign real value with .addValue() method
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", "%" + StringUtils.lowerCase(request.getFirstName()) + "%")
                .addValue("lastName", "%" + StringUtils.lowerCase(request.getLastName()) + "%")
                .addValue("residentCity", "%" + StringUtils.lowerCase(request.getResidentCity()) + "%")
                .addValue("residentState", request.getResidentState())
                .addValue("universityName", "%" + StringUtils.lowerCase(request.getUniversityName()) + "%")
                .addValue("grade", request.getGrade())
                .addValue("major", "%" + StringUtils.lowerCase(request.getMajor()) + "%");
        // Building the Query based on whether the field from the request object is
        // either null or not.
        StringBuilder sql = new StringBuilder(SELECT_VERSION_UNIVERSITY_NAME)
                .append(SqlUtils.andAddCondition(AND_FIRSTNAME, request.getFirstName()))
                .append(SqlUtils.andAddCondition(AND_LASTNAME, request.getLastName()))
                .append(SqlUtils.andAddCondition(AND_RESIDENT_CITY, request.getResidentCity()))
                .append(SqlUtils.andAddCondition(AND_RESIDENT_STATE, request.getResidentState()))
                .append(SqlUtils.andAddCondition(AND_UNIVERSITY_NAME, request.getUniversityName()))
                .append(SqlUtils.andAddCondition(AND_GRADE, request.getGrade()))
                .append(SqlUtils.andAddCondition(AND_MAJOR, request.getMajor()));
        // jdbctemplate talks to the database with your query and returns a List that
        // you specify (e.g. StudentDto).
        return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(StudentDto.class, true));
    }

    public Optional<StudentAccountDetailsDto> findByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);

        StringBuilder sql = new StringBuilder(SELECT_VERSION_UNIVERSITY_NAME)
                .append(SqlUtils.andAddCondition(AND_EMAIL, email));

        try {
            //Should the query be successful
            StudentAccountDetailsDto student = jdbcTemplate.queryForObject(
                    sql.toString(),
                    params,
                    new BeanPropertyRowMapper<>(StudentAccountDetailsDto.class, true)
            );
            //we will return the StudentUpdateDto object back to the service
            return Optional.ofNullable(student);
        } catch (EmptyResultDataAccessException ex) {
            //Otherwise, this exception will be thrown because it couldn't find what we were looking for
            //and we need to return an empty Optional
            return Optional.empty();
        }
    }

    public Optional<StudentUpdateDto> findStudentByEmail(String email){
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);

        StringBuilder sql = new StringBuilder(SELECT_VERSION_UNIVERSITY_ID)
                .append(SqlUtils.andAddCondition(AND_EMAIL, email));

        try{
            //Successful Student Query
            StudentUpdateDto student = jdbcTemplate.queryForObject(
                    sql.toString(),
                    params,
                    new BeanPropertyRowMapper<>(StudentUpdateDto.class, true)
            );
            //we will return the StudentUpdateDto object back to the service
            return Optional.ofNullable(student);
        }catch (EmptyResultDataAccessException ex){
            //Couldn't find our student from our query, so this exception is thrown
            return Optional.empty();
        }
    }

    /**
     * Inserts a new student record into the database, specifically inserts a new
     * student into the student table, using the details provided in the
     * {@link StudentSignupRequest} object and returns the number of rows affected
     * by the operation.
     *
     * @param student the {@link StudentSignupRequest} object containing the
     *                student's details such as
     *                first name, last name, resident city, resident state,
     *                university ID, grade, major,
     *                email, and social media link.
     * @return an integer indicating the number of rows affected by the insert
     *         operation. A value greater
     *         than 0 indicates that the operation was successful
     */
    public int insertNewStudent(StudentSignupRequest student) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", student.getFirstName())
                .addValue("lastName", student.getLastName())
                .addValue("residentCity", student.getResidentCity())
                .addValue("residentState", student.getResidentState())
                .addValue("universityId", student.getUniversityId())
                .addValue("grade", student.getGrade())
                .addValue("major", student.getMajor())
                .addValue("email", student.getEmail())
                .addValue("socialMediaLink", student.getSocialMediaLink());
        try {
            return jdbcTemplate.update(INSERT_NEW_STUDENT, params);// update() returns an "int" to indicate how many
                                                                   // rows
                                                                   // Were affected by the SQL Operation. If int > 0,
                                                                   // Then operation was successful; else no rows were
                                                                   // inserted/updated...
        } catch (Exception ex) {
            throw new RuntimeException("Student insertion failed due to a database error", ex);
        }
    }

    public int updateStudent(StudentUpdateDto updatedStudent){
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", updatedStudent.getId())
                .addValue("firstName", updatedStudent.getFirstName())
                .addValue("lastName", updatedStudent.getLastName())
                .addValue("residentCity", updatedStudent.getResidentCity())
                .addValue("residentState", updatedStudent.getResidentState())
                .addValue("universityId", updatedStudent.getUniversityId())
                .addValue("grade", updatedStudent.getGrade())
                .addValue("major", updatedStudent.getMajor())
                .addValue("email", updatedStudent.getEmail())
                .addValue("socialMediaLink", updatedStudent.getSocialMediaLink());

        StringBuilder sql = new StringBuilder(UPDATE_STUDENT_INFO);
        try {
            return jdbcTemplate.update(sql.toString(), params);
        }catch (Exception ex){
            throw new RuntimeException("Student update failed due to a database error", ex);
        }

    }

}
