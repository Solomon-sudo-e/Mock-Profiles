INSERT INTO profile (profile_name, biography, status, password_access, privacy) VALUES ('Solomon_Hufford', 'Creator of the project, and first test!', 'ISONLINE', 'orange_juice', 'PUBLIC');
INSERT INTO profile (profile_name, biography, status, password_access, privacy) VALUES ('Stephanie_Back', 'Mentor of Promineo Tech''s backend program! Excited to explore the many possibilities of coding!', 'DONOTDISTURB', 'Mentor101', 'PUBLIC');
INSERT INTO profile (profile_name, biography, status, password_access, privacy) VALUES ('Mike_Goeres', 'New mentor of Promineo Tech''s backend program! Look for the good, and you will find the good!', 'ISONLINE', 'YOLO', 'PRIVATE');
INSERT INTO profile (profile_name, biography, status, password_access, privacy) VALUES ('Kyle_Miles', 'Friendly neighborhood genius of the Promineo Tech backend cohorts!', 'ISONLINE', 'JPAROX', 'PUBLIC');
INSERT INTO profile (profile_name, biography, status, password_access, privacy) VALUES ('BJ_Hile', 'Teacher of Promineo Tech''s backend program. Excited to see students grow and become masters of their passion!', 'ISOFFLINE', 'JDBCROX', 'PRIVATE');
INSERT INTO profile (profile_name, biography, status, password_access, privacy) VALUES ('Lisa_Smith', 'Backend teacher for the Promineo Tech''s backend program. I love watching students find their passion in and out of coding.', 'ISOFFLINE', 'studystudystudy', 'PUBLIC');
INSERT INTO profile (profile_name, biography, status, password_access, privacy) VALUES ('Nick_Suwyn', 'CEO of Promineo Tech.', null, 'Hidden102131312', 'PRIVATE');
INSERT INTO profile (profile_name, biography, status, password_access, privacy) VALUES ('Dr_Rob', 'Master of SQL and Springboot, one of the funniest and fun to talk to professors!', 'ISONLINE', 'jpaIsTrash', 'PUBLIC');
INSERT INTO profile (profile_name, biography, status, password_access, privacy) VALUES ('Jolene_Melanson', 'Mentor of Promineo Tech''s backend corhorts! I am passionate about my studnets and showing them what they truly can do! Don''t call yourself dumb!', 'ISONLINE', '16x16', 'PUBLIC');

INSERT INTO user_data (user_name, date_of_birth, age, gender, email, location) VALUES ('Turtle_Man', '02252002', 21, 'male', 'solo.hufford@gmail.com', 'Siloam Springs, Arkansas');
INSERT INTO user_data (user_name, date_of_birth, age, gender, email, location) VALUES ('Mentoreo_Majesty', '04162000', 23, 'female', 'stephanolehe@promineotech.com', 'Somewhere down yonder');
INSERT INTO user_data (user_name, date_of_birth, age, gender, email, location) VALUES ('ManWithAPlan', '03121995', 28, null, 'michaelresources@promineo.tech', 'Somewhere down yonder');
INSERT INTO user_data (user_name, date_of_birth, age, gender, email, location) VALUES ('BurnoutDoesntExist', '02221999', 24, null, 'kmillion@gmail.com', 'Somewhere only I know...');
INSERT INTO user_data (user_name, date_of_birth, age, gender, email, location) VALUES ('EducationRox', '04161995', 28, 'male', 'Retreat43@gmail.com', 'Teaching but I wish I was on a beach.');
INSERT INTO user_data (user_name, date_of_birth, age, gender, email, location) VALUES ('EducationRocks', '07281995', 27, 'female', 'lucydoggo@comcast.net', 'hiding');
INSERT INTO user_data (user_name, date_of_birth, age, gender, email, location) VALUES ('Ceo_and_Cofounder', '08141998', 24, null, 'nick@promineotech.com', 'Working hard or hardly working. Can''t catch me!');
INSERT INTO user_data (user_name, date_of_birth, age, gender, email, location) VALUES ('jpaISTRASH','05122010', 13, null, 'docRob@genius.com', 'somewhere making a new class video');
INSERT INTO user_data (user_name, date_of_birth, age, gender, email, location) VALUES ('creativegenius','03251999', 24, 'female', 'jolenemelone@hotmail.com', 'somewhere secret');

INSERT INTO friend (profile_fk1, profile_fk2) VALUES (1,2);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (1,2);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (1,3);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (1,4);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (1,5);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (1,6);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (1,7);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (1,8);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (1,9);

INSERT INTO friend (profile_fk1, profile_fk2) VALUES (2,3);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (2,4);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (2,5);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (2,6);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (2,7);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (2,8);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (2,9);

INSERT INTO friend (profile_fk1, profile_fk2) VALUES (3,6);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (3,7);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (3,4);

INSERT INTO friend (profile_fk1, profile_fk2) VALUES (4,5);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (4,8);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (4,9);

INSERT INTO friend (profile_fk1, profile_fk2) VALUES (5,7);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (5,9);

INSERT INTO friend (profile_fk1, profile_fk2) VALUES (6,7);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (6,8);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (6,9);

INSERT INTO friend (profile_fk1, profile_fk2) VALUES (7,8);
INSERT INTO friend (profile_fk1, profile_fk2) VALUES (7,9);

INSERT INTO user_profile (user_id, profile_pk) VALUES (1,2);
INSERT INTO user_profile (user_id, profile_pk) VALUES (1,4);
INSERT INTO user_profile (user_id, profile_pk) VALUES (2,3);
INSERT INTO user_profile (user_id, profile_pk) VALUES (2,5);
INSERT INTO user_profile (user_id, profile_pk) VALUES (2,2);



