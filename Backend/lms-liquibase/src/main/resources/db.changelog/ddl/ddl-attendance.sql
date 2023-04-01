-- liquibase formatted sql

-- changeset rajes:1678898835087-5
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables WHERE table_schema = 'hello' AND table_name = 'HOLIDAY_CALENDAR';
CREATE TABLE HOLIDAY_CALENDAR (ID INT AUTO_INCREMENT NOT NULL, DATE VARCHAR(50) NULL, `DESCRIPTION` VARCHAR(100) NULL, CITY VARCHAR(50) NULL, YEAR INT NULL, CONSTRAINT PK_HOLIDAY_CALENDAR PRIMARY KEY (ID));

-- changeset rajes:1678898835087-6
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables WHERE table_schema = 'hello' AND table_name = 'employee';
CREATE TABLE employee (employee_Id INT NOT NULL, Name VARCHAR(255) NULL, CONSTRAINT PK_EMPLOYEE PRIMARY KEY (employee_Id));