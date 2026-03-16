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
    major varchar(255) NOT NULL,
    email varchar (255) NOT NULL,
    social_media_link varchar(255) NULL,
    -- CHANGE NOTE (Rohit Vijai, 2026-03-15): Added UNIQUE constraint on student.email to enforce one student profile per email address at the DB level.
    CONSTRAINT uq_student_email UNIQUE (email),
    FOREIGN KEY (university_id) REFERENCES university(id)
);

CREATE TABLE app_user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  role varchar(20) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL
  -- CHANGE NOTE (Rohit Vijai, 2026-03-15): Added UNIQUE constraint on app_user.email to prevent duplicate accounts from being created for the same email address.
  ,CONSTRAINT uq_app_user_email UNIQUE (email)
);