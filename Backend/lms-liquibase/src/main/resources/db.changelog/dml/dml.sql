-- liquibase formatted sql

-- changeset rajes:1678897217420-1
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM employee WHERE employee_id = 1;
insert into employee(employee_id, Name) values(1, "Pratap");
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM employee WHERE employee_id = 2;
insert into employee(employee_id, Name) values(2, "Pratap");
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM employee WHERE employee_id = 3;
insert into employee(employee_id, Name) values(3, "Pratap");
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM employee WHERE employee_id = 4;
insert into employee(employee_id, Name) values(4, "Pratap");