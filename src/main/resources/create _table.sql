create table  User (
  USER_ID bigint NOT NULL PRIMARY KEY,
  LOGIN varchar(10),
  PASSWORD varchar(8),
  FIRST_NAME varchar(50),
  LAST_NAME varchar(50),
  GENDER char(1) ,
  BIRTH_DATE date,
  DESCRIPTION varchar(255)
);