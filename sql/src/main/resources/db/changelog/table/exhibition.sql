--liquibase formatted sql

--changeset immess:add_exhibition_table
create table exhibition
(
  id         identity primary key,
  name       longnvarchar not null,
  start_date date         not null,
  city       longnvarchar not null
)
