--liquibase formatted sql
--changeset Dmitriy Chereukho:2
create table "test_cases"(
    id varchar(64) not null constraint test_cases_pk primary key,
    name varchar(64) not null,
    tested int not null,
    created_at DATE not null,
    last_update DATE not null
);