--liquibase formatted sql

--changeset immess:add_cattery_table
create table cattery
(
  id    identity        primary key,
  name  longnvarchar  not null unique,
  owner longnvarchar  not null,
  city  longnvarchar  not null,
)
