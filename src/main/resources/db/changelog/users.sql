--liquibase formatted sql
--changeset Dmitriy Chereukho:1
create table "users"(
    id uuid not null constraint users_pk primary key,
    name varchar(64) not null,
    surname varchar(64) not null,
    patronymic varchar(64),
    age int not null,
    email varchar(128) not null,
    password varchar(256) not null,
    phone_num varchar(32),
    role varchar(16) not null
);

INSERT INTO users (id, name, surname, age, email, password, role)
VALUES ('a494b088-e627-4ed0-a550-586a210ba6f3', 'admin_name', 'admin_surname', 18, 'adminemail@em.ru', '$2a$04$Z/Lxr1OFeK/EwhGP5lcWhuwwj2Luhy/oN4Vk.2xsOvlL4KaiNoyNe', 'ADMIN');