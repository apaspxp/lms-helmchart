-- liquibase formatted sql

-- changeset Sarthak:1681902748664-3
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables WHERE table_schema = 'LMS_PROFILE_SERVICE' AND table_name = 'EMPLOYEE';
CREATE TABLE EMPLOYEE (
  EMPLOYEE_ID INT NOT NULL AUTO_INCREMENT,
  FIRSTNAME VARCHAR(255) NOT NULL,
  MIDDLENAME VARCHAR(255) NOT NULL,
  LASTNAME VARCHAR(255) NOT NULL,
  EMAIL VARCHAR(255) NOT NULL,
  MOBILENO BIGINT NOT NULL,
  DESIGNATION VARCHAR(255) NOT NULL,
  MANAGERID INT NOT NULL,
  STATUS VARCHAR(255) NOT NULL,
  STARTDATE DATE NOT NULL,
  ENDDATE DATE NOT NULL,
  LASTMANAGERCHANGEDATE DATE NOT NULL,
  LOCATIONID INT NOT NULL,
  PRIMARY KEY (EMPLOYEE_ID)
);

-- changeset Sarthak:1681902748664-4
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables WHERE table_schema = 'LMS_PROFILE_SERVICE' AND table_name = 'ADDRESS';
CREATE TABLE ADDRESS (
  ADDRESS_ID INT NOT NULL AUTO_INCREMENT,
  EMPLOYEE_ID INT DEFAULT NULL,
  ADDRESSLINE1 VARCHAR(255) DEFAULT NULL,
  ADDRESSLINE2 VARCHAR(255) DEFAULT NULL,
  LOCALITY VARCHAR(255) DEFAULT NULL,
  PINCODE INT DEFAULT NULL,
  CITY VARCHAR(255) DEFAULT NULL,
  STATE VARCHAR(255) DEFAULT NULL,
  COUNTRY VARCHAR(255) DEFAULT NULL,
  REGION VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (ADDRESS_ID)
);