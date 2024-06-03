--liquibase formatted sql
--changeset Dmitriy Chereukho:5
ALTER TABLE problems ADD COLUMN description varchar(4096);