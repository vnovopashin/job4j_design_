create table body_car (
id serial primary key,
name varchar(50)
);

create table engine (
id serial primary key,
name varchar(50)
);

create table transmission (
id serial primary key,
name varchar(50)
);

create table car (
id serial primary key,
name varchar(50),
body_car_id int references body_car(id),
engine_id int references engine(id),
transmission_id int references transmission(id)
);

insert into body_car (name)
values ('hatchback'), ('sedan'), ('station wagon'), ('coupe');

insert into engine (name)
values ('gas engine'), ('diesel engine'), ('electrical engine');

insert into transmission (name)
values ('automatic'), ('manual'), ('variable speed drive');

insert into car (name, body_car_id, engine_id, transmission_id)
values ('Lada Vesta', 2, 1, 2), ('Nissan Patrol', 3, 2, 2), ('Ford Focus', 1, 1, 1);


select c.name as car_name, bc.name as body, e.name as engine, t.name as transmission
from car c
left join body_car bc on c.body_car_id = bc.id
left join engine e on c.engine_id = e.id
left join transmission t on c.transmission_id = t.id

select bc.name from body_car bc left join car c on c.body_car_id = bc.id where c.name is null;
select e.name from engine e left join car c on c.engine_id = e.id where c.name is null;
select t.name from transmission t left join car c on c.transmission_id = t.id where c.name is null;
