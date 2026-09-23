create table role (
    id serial primary key,
    name_role varchar(50)
);

create table rules (
  id serial primary key,
  type varchar(50)
);

create table category (
  id serial primary key,
  name_category varchar(100)
);

create table state (
  id serial primary key,
  status varchar(50)
);

create table users (
    id serial primary key,
    name_user varchar(50),
    email varchar(50),
    role_id int,
    foreign key (role_id) references role(id) on delete CASCADE
);

create table item (
  id serial primary key,
  title varchar(100),
  users_id int,
  state_id int,
  category_id int,
  foreign key (users_id) references users(id) on delete CASCADE,
  foreign key (state_id) references state(id) ON DELETE CASCADE,
  foreign key (category_id) references category(id) on delete cascade
);

create table role_rules (
  id serial primary key,
  role_id int,
  rules_id int,
  foreign key (role_id) references role(id) on delete cascade,
  foreign key (rules_id) references rules(id) on delete cascade
);

create table comments (
  id serial primary key,
  description text,
  item_id int,
  foreign key (item_id) references item(id) on delete cascade
);

create table attaches (
  id serial primary key,
  attach_file text,
  item_id int,
  foreign key (item_id) references item(id) on delete cascade
);

\dt;
