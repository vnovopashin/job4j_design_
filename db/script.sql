CREATE TABLE books (
	id SERIAL PRIMARY KEY,
	title VARCHAR(255),
	author VARCHAR(255),
	price NUMERIC(8, 2),
	amount INT
);

INSERT INTO books(title, author, price, amount)
VALUES('Java. Библиотека профессионала', 'Кей Хорстманн', 1500, 5);

UPDATE books
SET price = 1350, amount = 8;

SELECT * FROM books;

DELETE FROM books;
