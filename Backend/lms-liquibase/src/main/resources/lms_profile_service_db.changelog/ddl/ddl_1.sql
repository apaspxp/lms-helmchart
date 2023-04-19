-- liquibase formatted sql

-- changeset Sarthak:1681902748664-3
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables WHERE table_schema = 'LMS_PROFILE_SERVICE' AND table_name = 'TEST';
CREATE TABLE TEST (
    ID INT
);
