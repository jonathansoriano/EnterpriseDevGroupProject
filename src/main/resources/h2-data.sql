-- Total of 8 Universities in this in-memory database
-- Insert Universities within 50 miles of Cincinnati
INSERT INTO university (name) VALUES ('University of Cincinnati');
INSERT INTO university (name) VALUES ('Northern Kentucky University');
INSERT INTO university (name) VALUES ('Xavier University');
INSERT INTO university (name) VALUES ('Miami University');
INSERT INTO university (name) VALUES ('Thomas More University');
INSERT INTO university (name) VALUES ('Cincinnati State Technical and Community College');
INSERT INTO university (name) VALUES ('Mount St. Joseph University');
INSERT INTO university (name) VALUES ('Cincinnati Christian University');

-- Total of 33 Students in this in-memory database
-- Insert Students for University of Cincinnati (id=1)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Sarah', 'Johnson', 'Cincinnati', 'OH', 1, 'Junior', 'Computer Science', 'sarah.johnson@mail.uc.edu', 'https://linkedin.com/in/sarahjohnson');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Michael', 'Chen', 'Mason', 'OH', 1, 'Senior', 'Electrical Engineering', 'michael.chen@mail.uc.edu', 'https://twitter.com/mchen_uc');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Emily', 'Rodriguez', 'Blue Ash', 'OH', 1, 'Freshman', 'Biology', 'emily.rodriguez@mail.uc.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('James', 'Williams', 'Norwood', 'OH', 1, 'Sophomore', 'Business Administration', 'james.williams@mail.uc.edu', 'https://instagram.com/jameswilliams');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Jessica', 'Brown', 'Hyde Park', 'OH', 1, 'Senior', 'Mechanical Engineering', 'jessica.brown@mail.uc.edu', 'https://linkedin.com/in/jessicabrown');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('David', 'Martinez', 'Clifton', 'OH', 1, 'Junior', 'Psychology', 'david.martinez@mail.uc.edu', NULL);

-- Insert Students for Northern Kentucky University (id=2)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Ashley', 'Taylor', 'Florence', 'KY', 2, 'Sophomore', 'Marketing', 'taylora1@nku.edu', 'https://linkedin.com/in/ashleytaylor');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Brandon', 'Anderson', 'Covington', 'KY', 2, 'Freshman', 'Information Technology', 'andersonb2@nku.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Christopher', 'Jackson', 'Fort Thomas', 'KY', 2, 'Junior', 'Criminal Justice', 'jacksonc4@nku.edu', 'https://instagram.com/chrisjackson');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Amanda', 'White', 'Highland Heights', 'KY', 2, 'Sophomore', 'Nursing', 'whitea5@nku.edu', NULL);

-- Insert Students for Xavier University (id=3)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Ryan', 'Harris', 'Norwood', 'OH', 3, 'Junior', 'Finance', 'harrisr@xavier.edu', 'https://linkedin.com/in/ryanharris');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Lauren', 'Martin', 'Cincinnati', 'OH', 3, 'Senior', 'Communications', 'martinl@xavier.edu', 'https://twitter.com/laurenmartin');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Daniel', 'Thompson', 'Oakley', 'OH', 3, 'Freshman', 'Philosophy', 'thompsond@xavier.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Nicole', 'Garcia', 'Mount Lookout', 'OH', 3, 'Sophomore', 'English', 'garcian@xavier.edu', 'https://instagram.com/nicolegarcia');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Kevin', 'Lee', 'Evanston', 'OH', 3, 'Junior', 'Political Science', 'leek@xavier.edu', NULL);

-- Insert Students for Miami University (id=4)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Samantha', 'Wilson', 'Oxford', 'OH', 4, 'Sophomore', 'Architecture', 'wilsons2@miamioh.edu', 'https://linkedin.com/in/samanthawilson');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Tyler', 'Moore', 'Hamilton', 'OH', 4, 'Senior', 'Accounting', 'mooret3@miamioh.edu', 'https://twitter.com/tylermoore');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Rachel', 'Davis', 'Fairfield', 'OH', 4, 'Freshman', 'Chemistry', 'davisr4@miamioh.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Andrew', 'Miller', 'West Chester', 'OH', 4, 'Junior', 'History', 'millera5@miamioh.edu', 'https://instagram.com/andrewmiller');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Olivia', 'Clark', 'Liberty Township', 'OH', 4, 'Senior', 'International Studies', 'clarko6@miamioh.edu', 'https://linkedin.com/in/oliviaclark');

-- Insert Students for Thomas More University (id=5)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Matthew', 'Lewis', 'Crestview Hills', 'KY', 5, 'Freshman', 'Education', 'lewism@thomasmore.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Brittany', 'Walker', 'Erlanger', 'KY', 5, 'Sophomore', 'Biology', 'walkerb@thomasmore.edu', 'https://twitter.com/brittanywalker');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Joshua', 'Hall', 'Independence', 'KY', 5, 'Junior', 'Sports Management', 'hallj@thomasmore.edu', 'https://linkedin.com/in/joshuahall');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Kayla', 'Young', 'Burlington', 'KY', 5, 'Senior', 'Theatre Arts', 'youngk@thomasmore.edu', NULL);

-- Insert Students for Cincinnati State (id=6)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Eric', 'King', 'Clifton', 'OH', 6, 'Freshman', 'Information Technology', 'eric.king@cincinnatistate.edu', 'https://instagram.com/ericking');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Stephanie', 'Wright', 'Northside', 'OH', 6, 'Sophomore', 'Graphic Design', 'stephanie.wright@cincinnatistate.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Justin', 'Lopez', 'Price Hill', 'OH', 6, 'Freshman', 'Culinary Arts', 'justin.lopez@cincinnatistate.edu', 'https://twitter.com/justinlopez');

-- Insert Students for Mount St. Joseph University (id=7)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Melissa', 'Hill', 'Delhi Township', 'OH', 7, 'Junior', 'Social Work', 'melissa.hill@msj.edu', 'https://linkedin.com/in/melissahill');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Nathan', 'Scott', 'Green Township', 'OH', 7, 'Senior', 'Art Therapy', 'nathan.scott@msj.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Victoria', 'Green', 'Cheviot', 'OH', 7, 'Sophomore', 'Music Education', 'victoria.green@msj.edu', 'https://instagram.com/victoriagreen');

-- Insert Students for Cincinnati Christian University (id=8)
INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Alexander', 'Adams', 'Madeira', 'OH', 8, 'Freshman', 'Biblical Studies', 'alexander.adams@ccuniversity.edu', NULL);

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Hannah', 'Baker', 'Kenwood', 'OH', 8, 'Junior', 'Ministry', 'hannah.baker@ccuniversity.edu', 'https://twitter.com/hannahbaker');

INSERT INTO student (first_name, last_name, resident_city, resident_state, university_id, grade, major, email, social_media_link)
VALUES ('Zachary', 'Nelson', 'Montgomery', 'OH', 8, 'Senior', 'Youth Ministry', 'zachary.nelson@ccuniversity.edu', 'https://linkedin.com/in/zacharynelson');

-- Insert Users (all with password: "passw0rd!" hashed via BCryptPasswordEncoder with cost factor 10. ALl have the same password for simplicity and testing purposes only)
-- University of Cincinnati
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'sarah.johnson@mail.uc.edu', '$2a$10$cT37ge3YHk2NxIjDvUpns.CucoBA8cQ.DzJXoqcIVJ6nQUZpB9SVa');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'michael.chen@mail.uc.edu', '$2a$10$o.pLS/2KqLaqbfCKJsW2MOzrnkcrPxbvStGyYNYh0Avog1fZFo8f.');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'emily.rodriguez@mail.uc.edu', '$2a$10$ECB6kM6/GcwBW5i6XFTAXeN6YULMIRjkLq1tcRZw8ivx6NwQ4DJ8a');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'james.williams@mail.uc.edu', '$2a$10$d3/5pHuKKtDaxsGVp2i2tOID5Mf0vTMTa9kLhXcbRfOVIMJ6v42.e');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'jessica.brown@mail.uc.edu', '$2a$10$YykVVHgso/5FDg602WFqxeiEvLUm4y3P0J0uAB7.eyZyteYZbAHgO');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'david.martinez@mail.uc.edu', '$2a$10$XevAUZl2Q3P04CeA9aLm5u49p74VCrzZ8ir.dLM0jjr4sb1HO0Vo6');

-- Northern Kentucky University
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'taylora1@nku.edu', '$2a$10$313TVQ9uxoQRdgFnJF.knebscpNDQqrTfu2VQp3f.gJrrS3/sCaKm');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'andersonb2@nku.edu', '$2a$10$8gX2MIe1QSb66PDWwXhDPeGtrOFkdHDyy8VoHUGR9coCuGwH10kwm');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'jacksonc4@nku.edu', '$2a$10$P4tgvaBPwQHKkP71pq8S6Ox956oeKQ0hcYhJR2vmevzqvxPs6zVLC');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'whitea5@nku.edu', '$2a$10$3y9RGf2251hACx8Mr0y3heA5BB/5RMkbmEY3d4eyJE5yhWOgPPQRK');

-- Xavier University
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'harrisr@xavier.edu', '$2a$10$jWViF6ZPDaOmpMAQmRiFv.JplTdWq/ilgjMaoo3l8FwFQy18lG.hy');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'martinl@xavier.edu', '$2a$10$QU6Xb.3ZMxE58wSZumlEMueqwDlX9hKPLV8TVUoVem5ZEhdYuMi2K');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'thompsond@xavier.edu', '$2a$10$3.hcoyppJvzevkT8ztAqXu5GVXLhvYpoVb4H3sQWtoEziuJCHYcra');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'garcian@xavier.edu', '$2a$10$y4BnA0j9NTn34Uoko53cPujN5ThmMHkY9CDP8TZMdEQ9mSn6p/IKK');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'leek@xavier.edu', '$2a$10$6IMfyjbImfyKWdrNShCKmuA1oz0CiV9d0kf5ouIv2iDOi4Bg5DXa2');

-- Miami University
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'wilsons2@miamioh.edu', '$2a$10$bbSr5eoanm68InYDM0EWzOTNz09cqWKoTLEcfaQhHfR01x3oKIuzC');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'mooret3@miamioh.edu', '$2a$10$/21dn57qRf5sCjPDXR954eL0SJGbD8GL99eee.4DtpWg1eSMfVRH6');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'davisr4@miamioh.edu', '$2a$10$iCj4.7buwMP7TXR8AW3iz.vuu74rAIIJuSGTeHtFTamCQiY9ZupKe');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'millera5@miamioh.edu', '$2a$10$DADE6NRGnIMXLWDWh8NttuftbKdHZujoi8/kttVHW1eKOqpk9z5sK');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'clarko6@miamioh.edu', '$2a$10$C5OgFFCmpzx0GOzKSbhEXeFtuczLuoD/4MeeElv/sun4d.X33T1Ti');

-- Thomas More University
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'lewism@thomasmore.edu', '$2a$10$uebZdei9RReIqom/Z7ujtOMxGDoqlOw5P11hWX16putaTMrVfnreq');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'walkerb@thomasmore.edu', '$2a$10$SedzCst.GqOtNSF2YKsZm.V2HZbFzwHRoyIfr8KjyzU7Ma6A2N16K');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'hallj@thomasmore.edu', '$2a$10$9skKCE4rX/cH6Aab.NQ3aeETJNG6hpt4aPaJBjdBBWk9ApH2Li1Si');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'youngk@thomasmore.edu', '$2a$10$vkimD2/HLWThr9Gp9Cdw5.zlK0T77lc2.jvlpa0uJ77YOtaJNStGC');

-- Cincinnati State
INSERT INTO app_user ( role, email, password)
VALUES ( 'USER', 'eric.king@cincinnatistate.edu', '$2a$10$Ga.70LN7vtOPxjAHXAcMLOY1se6TlJUNmVPpHVKTkIGhcCBEQPKZm');
INSERT INTO app_user ( role, email, password)
VALUES ( 'USER', 'stephanie.wright@cincinnatistate.edu', '$2a$10$jZ4OlOW/0rtUA/A38dIJz.S3K3gZOysccxgQdXA//VVIFrey2R56C');
INSERT INTO app_user ( role, email, password)
VALUES ( 'USER', 'justin.lopez@cincinnatistate.edu', '$2a$10$z63S027NQ/u8iXpU1M2sPOf2HK2W2FMAql.9Qm8S076tQyrzfAbai');

-- Mount St. Joseph University
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'melissa.hill@msj.edu', '$2a$10$7f834JkU14vjcUrd7PDpTO.BUhydvKFCkirD0YQ.p0LiHQ5k7u5nK');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'nathan.scott@msj.edu', '$2a$10$LvlHkgLJUAGB0UDEjkFnluwlcN2jh7eLQ.PSlFeL5DXAIrUvtsKyW');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'victoria.green@msj.edu', '$2a$10$ceLrBYQ0mLHOuhFx6k8JKuDI67Ne9aZNkdvafSUejLyjNM/MFWXHy');

-- Cincinnati Christian University
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'alexander.adams@ccuniversity.edu', '$2a$10$U4GHwqKd4bD/RzMu52Kn4eGKUdGq2InS7YGS/vE4cFaBbff9YV1f6');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'hannah.baker@ccuniversity.edu', '$2a$10$PQ0LqMajYXPtt62UKxv/IuN5LgML6YqrgT6rajBbWnS.5jNNNEp0i');
INSERT INTO app_user ( role, email, password)
VALUES ('USER', 'zachary.nelson@ccuniversity.edu', '$2a$10$UjP6qpyyGeqynMpC8LVCe.VbyNWqdbVRBzbfTwM3fdZPXGJPZO8hO');