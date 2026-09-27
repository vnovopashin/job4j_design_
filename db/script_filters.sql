create table type(
                     id serial primary key,
                     name text
);

create table product(
                        id serial primary key ,
                        name text,
                        type_id int references type(id),
                        expired_date date,
                        price float
);

insert into type (name)
values ('Молоко'), ('Сыр'), ('Мясо'), ('Мороженое');

insert into product(name, type_id, expired_date, price)
values ('Простоквашино', 1, '11.10.2021', 50),
       ('Домик в деревне', 1, '8.10.2021', 48.5),
       ('Веселый молочник', 1, '01.09.2021', 45),
       ('Плавленый сыр', 2, '10.08.2021', 200),
       ('Мацарелла сыр', 2, '25.09.2021', 300),
       ('Пармезан сыр', 2, '03.10.2021', 290),
       ('Мясо говядина', 3, '11.10.2021', 350),
       ('Мясо свиное', 3, '08.10.2021', 300),
       ('мороженое в стаканчике', 4, '08.10.2021', 30),
       ('мороженое на палочке', 4, '08.10.2021', 28),
       ('шоколадное мороженое', 4, '08.10.2021', 28);
