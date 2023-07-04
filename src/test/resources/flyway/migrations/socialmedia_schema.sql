DROP TABLE IF EXISTS user_profile;
DROP TABLE IF EXISTS user_data;
DROP TABLE IF EXISTS friend;
DROP TABLE IF EXISTS profile;

CREATE TABLE profile (
profile_pk int NOT NULL AUTO_INCREMENT,
profile_name varchar(50) NOT NULL,
picture_id varchar(40),
date_created timestamp default current_timestamp not null,
biography varchar(200),
status enum('ISONLINE', 'ISOFFLINE', 'DONOTDISTURB'),
password_access varchar(30) NOT NULL,
privacy enum('PUBLIC', 'PRIVATE') NOT NULL,
notifications TEXT,
UNIQUE KEY (profile_name),
PRIMARY KEY (profile_pk)
);

CREATE TABLE friend (
friend_pk int unsigned NOT NULL AUTO_INCREMENT,
profile_fk1 int NOT NULL,
profile_fk2 int NOT NULL,
PRIMARY KEY (friend_pk),
FOREIGN KEY (profile_fk1) REFERENCES profile (profile_pk) ON DELETE CASCADE,
FOREIGN KEY (profile_fk2) REFERENCES profile (profile_pk) ON DELETE CASCADE

);

CREATE TABLE user_data (
user_id int NOT NULL AUTO_INCREMENT,
user_name varChar(100) NOT NULL,
profile_fk int unsigned,
profiles_with_access varChar(200),
friend_fk int unsigned,
date_of_birth varchar(9) NOT NULL,
age int NOT NULL,
gender enum ('FEMALE', 'MALE', 'OTHER'),
email varchar(30) NOT NULL,
location varchar(50),
PRIMARY KEY (user_id),
FOREIGN KEY (friend_fk) REFERENCES friend (friend_pk) ON DELETE CASCADE
);

CREATE TABLE user_profile (
user_id int NOT NULL,
profile_pk int NOT NULL,
request int,
FOREIGN KEY (user_id) REFERENCES user_data (user_id) ON DELETE CASCADE,
FOREIGN KEY (profile_pk) REFERENCES profile (profile_pk) ON DELETE CASCADE,
UNIQUE KEY (user_id, profile_pk)
);