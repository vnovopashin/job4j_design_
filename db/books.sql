CREATE TABLE books
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200),
    author_id INTEGER REFERENCES authors (id)
);

INSERT INTO books (name, author_id)
VALUES ('Евгений Онегин', 1);
INSERT INTO books (name, author_id)
VALUES ('Капитанская дочка', 1);
INSERT INTO books (name, author_id)
VALUES ('Дубровский', 1);
INSERT INTO books (name, author_id)
VALUES ('Мертвые души', 2);
INSERT INTO books (name, author_id)
VALUES ('Вий', 2);
