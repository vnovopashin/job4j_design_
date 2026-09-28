create table departments (
    id serial primary key,
    name varchar(50)
);

create table employees (
    id serial primary key,
    name varchar(50),
    departments_id int references departments(id)
);

insert into departments (name)
values ('department_1'), ('department_2'), ('department_3');


insert into employees (name, departments_id)
values (null, 1), (null, 1), (null, 2),
       ('employee_1', 1), ('employee_2', 1), ('employee_3', 2),
       ('employee_4', null), ('employee_5', null), ('employee_6', 3),
       ('employee_7', 1), ('employee_8', 1), ('employee_9', 3);


/*left join*/
select e.id as empl_id, e.name as empl_name, d.id as dep_id, d.name as dep_name from employees e
left join departments d on e.departments_id = d.id;

/*right join*/
select e.id as empl_id, e.name as empl_name, d.id as dep_id, d.name as dep_name from employees e
right join departments d on e.departments_id = d.id;

/*full join*/
select e.id as empl_id, e.name as empl_name, d.id as dep_id, d.name as dep_name from employees e
full join departments d on e.departments_id = d.id;

/*cross join*/
select e.id as empl_id, e.name as empl_name, d.id as dep_id, d.name as dep_name from employees e
cross join departments d;

/*left join find departments without employees*/
select d.name from departments d
left join employees e on e.departments_id = d.id
where e.id is null;

/*using left join and right join for equals result*/
select e.id as empl_id, e.name as empl_name, d.id as dep_id, d.name as dep_name
from employees e left join departments d  on  e.departments_id = d.id;

select e.id as empl_id, e.name as empl_name, d.id as dep_id, d.name as dep_name
from departments d  right join employees e on d.id = e.departments_id;

/*cross join in table teen*/
create table teen (
    id serial primary key,
    name varchar(50),
    gender varchar(50)
);

insert into teen (name, gender)
values ('Anna', 'female'), ('Kate', 'female'), ('Nancy', 'female'),
       ('Jack', 'male'), ('Harry', 'male'), ('Kevin', 'male');

/*cross join*/
select t1.name as a, t2.name as b
from  teen t1 cross join teen t2 where t1.gender <> t2.gender and t1.id < t2.id
