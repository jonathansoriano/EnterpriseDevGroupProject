--This in-memory database is going to be used for a University Student Directory program
--The universities and students in this database will consist of schools/students within 50 miles of Cincinnati
--The grade field in the student table represents the school year of the student (Freshman, Sophomore, Junior, Senior)

CREATE TABLE university (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(20) NOT NULL,
    last_name varchar(20) NOT NULL,
    resident_city varchar(40) NOT NULL,
    resident_state varchar(2) NOT NULL,
    university_id BIGINT NOT NULL,
    grade varchar(10) NOT NULL,
    email varchar (255) NOT NULL,
    social_media_link varchar(255) NULL,
    FOREIGN KEY (university_id) REFERENCES university(id)
);