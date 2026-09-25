create table author (
    id serial primary key,
    name_author varchar(255)
);

create table book (
    id serial primary key,
    title varchar(255),
    author_id int references author(id),
    price decimal (8, 2),
    amount int
);

insert into author (name_author)
values ('Кей Хорстманн'), ('Роберт Лафоре'), ('Николай Носов');

insert into book (title, author_id, price, amount)
values ('Java. Библиотека профессионала. Том 1', 1, 2499.5, 7),
        ('Java. Библиотека профессионала. Том 2', 1, 2850, 10),
       ('Структуры данных и алгоритмы java', 2, 2250, 9),
       ('Незнайка на луне', 3, 250.7, 15);

select name_author as Автор, title as Название
from author a
join book b on a.id = b.author_id
where title like '%java%' or title like '%Java%';

select name_author as Автор, sum(amount) as Количество
from author a
join book b on a.id = b.author_id
group by name_author
order by Количество;

select name_author as "Автор самой не дорогой книги"
from author a
join book b on a.id = b.author_id
where price = (select min(price) from book);
