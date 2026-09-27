select p.name as Наименование
from type t join product p on p.type_id = t.id
where t.name = 'Сыр';

select name as Продукт
from product
where name like '%мороженое%';

select name as Продукт
from product
where expired_date < current_date;

select name as Продукт
from product
where price = (select max(price) from product);

select t.name as Тип, count(p.name) as Количество
from type t join product p on p.type_id = t.id
group by t.name;

select p.name as Наименование
from type t join product p on p.type_id = t.id
where t.name in ('Сыр','Молоко');

select t.name as Тип
from type t join product p on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

select p.name as Наименование, t.name as Тип
from type t join product p on p.type_id = t.id;
