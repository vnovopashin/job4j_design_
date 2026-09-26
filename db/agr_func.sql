create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price)
values ('notebook', 5000), ('mobilephone', 1500), ('headphones', 1500),
       ('tv', 4500), ('play station', 5500), ('apple watch', 1500);

insert into people (name)
values ('Emily'), ('Michael'), ('James');

insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 1);
insert into devices_people(device_id, people_id) values (4, 2), (5, 2), (1, 2);
insert into devices_people(device_id, people_id) values (6, 3), (2, 3), (1, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join devices d on dp.device_id = d.id
join people p on dp.people_id = p.id
group by p.name;


select p.name, avg(d.price)
from devices_people as dp
join devices d on dp.device_id = d.id
join people p on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;
