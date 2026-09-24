create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values  ('cheetah', 50000, null),
        ('shark', 12000, '1242-02-13'),
        ('elephant', 15000, '1100-11-23'),
        ('golden fish', 35000, '1951-05-05'),
        ('javaman', 1000, '1024-01-01');

select * from fauna where name like ('%fish%');
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
