package com.jonathansoriano.enterprisedevgroupproject.repository;

import com.jonathansoriano.enterprisedevgroupproject.domain.StudentRequest;
import com.jonathansoriano.enterprisedevgroupproject.dto.StudentDto;
import com.jonathansoriano.enterprisedevgroupproject.util.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    // Alias columns to match StudentDto properties (camelCase), otherwise you'll get an exception due to mapping issues
    public static final String SELECT = """
            SELECT
              s.id AS id,
              s.first_name AS firstName,
              s.last_name AS lastName,
              s.resident_city AS residentCity,
              s.resident_state AS residentState,
              u.name AS universityName,
              s.grade AS grade,
              s.email AS email,
              s.social_media_link AS socialMediaLink
            FROM student s
            JOIN university u ON s.university_id = u.id
            WHERE 1 = 1
            """;

    public static final String AND_FIRSTNAME = "AND LOWER(s.first_name) LIKE :firstName";
    public static final String AND_LASTNAME = "AND LOWER(s.last_name) LIKE :lastName";
    public static final String AND_RESIDENT_CITY = "AND LOWER(s.resident_city) LIKE :residentCity";
    public static final String AND_RESIDENT_STATE = "AND s.resident_state = :residentState";
    public static final String AND_UNIVERSITY_NAME = "AND LOWER(u.name) LIKE :universityName";
    public static final String AND_GRADE = "AND s.grade = :grade";


    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StudentRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StudentDto> find(StudentRequest request){
        //MapSqlParameterSource allows you to use placeholders in Query build and assign real value with .addValue() method
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", "%" + StringUtils.lowerCase(request.getFirstName())+ "%")
                .addValue("lastName", "%" + StringUtils.lowerCase(request.getLastName()) + "%")
                .addValue("residentCity", "%" + StringUtils.lowerCase(request.getResidentCity()) + "%")
                .addValue("residentState", request.getResidentState())
                .addValue("universityName", "%" + StringUtils.lowerCase(request.getUniversityName()) + "%")
                .addValue("grade", request.getGrade());
        //Building the Query based on whether the field from the request object is either null or not.
        StringBuilder sql = new StringBuilder(SELECT)
                .append(SqlUtils.andAddCondition(AND_FIRSTNAME, request.getFirstName()))
                .append(SqlUtils.andAddCondition(AND_LASTNAME, request.getLastName()))
                .append(SqlUtils.andAddCondition(AND_RESIDENT_CITY, request.getResidentCity()))
                .append(SqlUtils.andAddCondition(AND_RESIDENT_STATE, request.getResidentState()))
                .append(SqlUtils.andAddCondition(AND_UNIVERSITY_NAME, request.getUniversityName()))
                .append(SqlUtils.andAddCondition(AND_GRADE, request.getGrade()));
        //jdbctemplate talks to the database with your query and returns a List that you specify (e.g. StudentDto).
        return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(StudentDto.class, true));
    }
}
