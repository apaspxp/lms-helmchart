-- liquibase formatted sql

-- changeset rajes:1678898835087-1
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables WHERE table_schema = 'LMSLEAVESERVICE' AND table_name = 'HOLIDAY_CALENDAR';
CREATE TABLE HOLIDAY_CALENDAR (ID INT AUTO_INCREMENT NOT NULL, DATE VARCHAR(50) NULL, `DESCRIPTION` VARCHAR(100) NULL, CITY VARCHAR(50) NULL, YEAR INT NULL, CONSTRAINT PK_HOLIDAY_CALENDAR PRIMARY KEY (ID));

-- changeset rajes:1678898835087-2
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables WHERE table_schema = 'LMSLEAVESERVICE' AND table_name = 'employee';
CREATE TABLE employee (employee_Id INT NOT NULL, Name VARCHAR(255) NULL, CONSTRAINT PK_EMPLOYEE PRIMARY KEY (employee_Id));

-- changeset Sarthak:1681904655480-4
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables WHERE table_schema = 'LMSATTENDANCESERVICE' AND table_name = 'LEAVE_ENTITLEMENT';
CREATE TABLE LEAVE_ENTITLEMENT (
    EMPLOYEE_ID VARCHAR(255) PRIMARY KEY,
    SICK_LEAVE_BALANCE INT,
    SICK_LEAVE_ENTITLE INT,
    CASUAL_LEAVE_BALANCE INT,
    CASUAL_LEAVE_ENTITLE INT,
    EARNED_LEAVE_BALANCE INT ,
    EARNED_LEAVE_ENTITLE INT,
    PATERNITY_LEAVE_BALANCE INT,
    PATERNITY_LEAVE_ENTITLE INT,
    LOP_OVERDRAFT INT
);