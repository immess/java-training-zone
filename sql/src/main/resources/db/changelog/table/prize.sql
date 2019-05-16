--liquibase formatted sql

--changeset immess:add_prize_table
create table prize
(
  id         identity primary key,
  cat_id     bigint       not null,
  exhib_id   bigint       not null,
  nomination longnvarchar not null,
  place      int          not null,

  constraint fk_prize_cat foreign key (cat_id) references cat(id),
  constraint fk_prize_exhib foreign key (exhib_id) references exhibition(id)
)
