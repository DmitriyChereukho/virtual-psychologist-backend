--liquibase formatted sql
--changeset Dmitriy Chereukho:4
ALTER TABLE "test_cases"
    ALTER COLUMN name TYPE varchar(512);