--liquibase formatted sql

--changeset immess:add_cat_table
create table cat
(
  id         identity primary key,
  cattery_id bigint,
  litter_id  bigint,
  owner      longnvarchar,
  name       longnvarchar,
  sex        smallint     not null,
  color      longnvarchar not null,
  sterilized smallint     not null,
  death_date date,

  constraint fk_cat_cattery foreign key (cattery_id) references cattery(id)
)
