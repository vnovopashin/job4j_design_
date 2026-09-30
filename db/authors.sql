CREATE TABLE authors
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO authors (name)
VALUES ('Александр Пушкин');
INSERT INTO authors (name)
VALUES ('Николай Гоголь');
