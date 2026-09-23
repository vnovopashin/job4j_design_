insert into role (name_role)
values ('admin'), ('user');

insert into rules (type)
values ('full access'), ('limited access');

insert into category (name_category)
values ('category#1'), ('category#2'), ('category#3');

insert into state (status)
values ('state#1'), ('state#2'), ('state#3');

insert into users (name_user, email, role_id)
values ('Ivanov', 'ivanov@gmail.com', 1),
       ('Petrov', 'petrov@gmail.com', 2),
       ('Sidorov', 'sidrov@gmail.com', 2);

insert into role_rules (role_id, rules_id)
values (1, 1),
       (2, 1);

insert into item (title, users_id, state_id, category_id)
values ('item#1', 1, 2, 1), ('item#2', 1, 1, 1), ('item#3', 2, 1, 1), ('item#4', 2, 1, 2);

insert into comments (description, item_id)
values ('description item#1', 1), ('description item#2', 2);

insert into attaches (attach_file, item_id)
values ('file#1', 1), ('file#2', 2), ('file#3', 1);
