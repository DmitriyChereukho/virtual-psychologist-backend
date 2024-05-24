--liquibase formatted sql
--changeset Dmitriy Chereukho:4
create table "problems"(
    id uuid not null constraint problems_pk primary key,
    name varchar(64) not null,
    test_case_id varchar(64) not null,
    test_case_link varchar(2048) not null,
    form_link varchar(2048) not null
);