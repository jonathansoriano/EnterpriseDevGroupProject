-- PostgreSQL schema for Student Directory
-- Auto-executed by Postgres on first container start via /docker-entrypoint-initdb.d/

CREATE TABLE university (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE student (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    resident_city VARCHAR(40) NOT NULL,
    resident_state VARCHAR(2) NOT NULL,
    university_id BIGINT NOT NULL,
    grade VARCHAR(10) NOT NULL,
    major VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    social_media_link VARCHAR(255) NULL,
    FOREIGN KEY (university_id) REFERENCES university(id)
);

CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
