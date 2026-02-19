-- Insert Universities within 50 miles of Cincinnati
INSERT INTO university (name) VALUES ('University of Cincinnati');
INSERT INTO university (name) VALUES ('Northern Kentucky University');
INSERT INTO university (name) VALUES ('Xavier University');
INSERT INTO university (name) VALUES ('Miami University');
INSERT INTO university (name) VALUES ('Thomas More University');
INSERT INTO university (name) VALUES ('Cincinnati State Technical and Community College');
INSERT INTO university (name) VALUES ('Mount St. Joseph University');
INSERT INTO university (name) VALUES ('Cincinnati Christian University');

-- Insert Students for University of Cincinnati (id=1)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Sarah', 'Johnson', 'Cincinnati', 'OH', 1, 'Junior', 'sarah.johnson@mail.uc.edu', 'https://linkedin.com/in/sarahjohnson');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Michael', 'Chen', 'Mason', 'OH', 1, 'Senior', 'michael.chen@mail.uc.edu', 'https://twitter.com/mchen_uc');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Emily', 'Rodriguez', 'Blue Ash', 'OH', 1, 'Freshman', 'emily.rodriguez@mail.uc.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('James', 'Williams', 'Norwood', 'OH', 1, 'Sophomore', 'james.williams@mail.uc.edu', 'https://instagram.com/jameswilliams');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Jessica', 'Brown', 'Hyde Park', 'OH', 1, 'Senior', 'jessica.brown@mail.uc.edu', 'https://linkedin.com/in/jessicabrown');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('David', 'Martinez', 'Clifton', 'OH', 1, 'Junior', 'david.martinez@mail.uc.edu', NULL);

-- Insert Students for Northern Kentucky University (id=2)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Ashley', 'Taylor', 'Florence', 'KY', 2, 'Sophomore', 'taylora1@nku.edu', 'https://linkedin.com/in/ashleytaylor');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Brandon', 'Anderson', 'Covington', 'KY', 2, 'Freshman', 'andersonb2@nku.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Christopher', 'Jackson', 'Fort Thomas', 'KY', 2, 'Junior', 'jacksonc4@nku.edu', 'https://instagram.com/chrisjackson');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Amanda', 'White', 'Highland Heights', 'KY', 2, 'Sophomore', 'whitea5@nku.edu', NULL);

-- Insert Students for Xavier University (id=3)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Ryan', 'Harris', 'Norwood', 'OH', 3, 'Junior', 'harrisr@xavier.edu', 'https://linkedin.com/in/ryanharris');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Lauren', 'Martin', 'Cincinnati', 'OH', 3, 'Senior', 'martinl@xavier.edu', 'https://twitter.com/laurenmartin');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Daniel', 'Thompson', 'Oakley', 'OH', 3, 'Freshman', 'thompsond@xavier.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Nicole', 'Garcia', 'Mount Lookout', 'OH', 3, 'Sophomore', 'garcian@xavier.edu', 'https://instagram.com/nicolegarcia');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Kevin', 'Lee', 'Evanston', 'OH', 3, 'Junior', 'leek@xavier.edu', NULL);

-- Insert Students for Miami University (id=4)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Samantha', 'Wilson', 'Oxford', 'OH', 4, 'Sophomore', 'wilsons2@miamioh.edu', 'https://linkedin.com/in/samanthawilson');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Tyler', 'Moore', 'Hamilton', 'OH', 4, 'Senior', 'mooret3@miamioh.edu', 'https://twitter.com/tylermoore');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Rachel', 'Davis', 'Fairfield', 'OH', 4, 'Freshman', 'davisr4@miamioh.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Andrew', 'Miller', 'West Chester', 'OH', 4, 'Junior', 'millera5@miamioh.edu', 'https://instagram.com/andrewmiller');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Olivia', 'Clark', 'Liberty Township', 'OH', 4, 'Senior', 'clarko6@miamioh.edu', 'https://linkedin.com/in/oliviaclark');

-- Insert Students for Thomas More University (id=5)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Matthew', 'Lewis', 'Crestview Hills', 'KY', 5, 'Freshman', 'lewism@thomasmore.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Brittany', 'Walker', 'Erlanger', 'KY', 5, 'Sophomore', 'walkerb@thomasmore.edu', 'https://twitter.com/brittanywalker');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Joshua', 'Hall', 'Independence', 'KY', 5, 'Junior', 'hallj@thomasmore.edu', 'https://linkedin.com/in/joshuahall');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Kayla', 'Young', 'Burlington', 'KY', 5, 'Senior', 'youngk@thomasmore.edu', NULL);

-- Insert Students for Cincinnati State (id=6)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Eric', 'King', 'Clifton', 'OH', 6, 'Freshman', 'eric.king@cincinnatistate.edu', 'https://instagram.com/ericking');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Stephanie', 'Wright', 'Northside', 'OH', 6, 'Sophomore', 'stephanie.wright@cincinnatistate.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Justin', 'Lopez', 'Price Hill', 'OH', 6, 'Freshman', 'justin.lopez@cincinnatistate.edu', 'https://twitter.com/justinlopez');

-- Insert Students for Mount St. Joseph University (id=7)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Melissa', 'Hill', 'Delhi Township', 'OH', 7, 'Junior', 'melissa.hill@msj.edu', 'https://linkedin.com/in/melissahill');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Nathan', 'Scott', 'Green Township', 'OH', 7, 'Senior', 'nathan.scott@msj.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Victoria', 'Green', 'Cheviot', 'OH', 7, 'Sophomore', 'victoria.green@msj.edu', 'https://instagram.com/victoriagreen');

-- Insert Students for Cincinnati Christian University (id=8)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Alexander', 'Adams', 'Madeira', 'OH', 8, 'Freshman', 'alexander.adams@ccuniversity.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Hannah', 'Baker', 'Kenwood', 'OH', 8, 'Junior', 'hannah.baker@ccuniversity.edu', 'https://twitter.com/hannahbaker');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, email, social_media_link)
VALUES ('Zachary', 'Nelson', 'Montgomery', 'OH', 8, 'Senior', 'zachary.nelson@ccuniversity.edu', 'https://linkedin.com/in/zacharynelson');