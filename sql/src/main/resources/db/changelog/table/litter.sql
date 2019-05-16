--liquibase formatted sql

--changeset immess:add_litter_table
create table litter
(
  id         identity primary key,
  cattery_id bigint not null,
  name       varchar(10),
  birth_date date   not null default current_date,
  dad_id     bigint not null,
  mom_id     bigint not null,

  constraint fk_litter_cattery foreign key (cattery_id) references cattery(id),
  constraint fk_litter_dad foreign key (dad_id) references cat(id),
  constraint fk_litter_mom foreign key (mom_id) references cat(id)
)
