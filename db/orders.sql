CREATE TABLE orders
(
    id SERIAL PRIMARY KEY,
    active BOOLEAN DEFAULT true,
    book_id INTEGER REFERENCES books (id),
    student_id INTEGER REFERENCES students (id)
);

INSERT INTO orders (book_id, student_id)
VALUES (1, 1);
INSERT INTO orders (book_id, student_id)
VALUES (3, 1);
INSERT INTO orders (book_id, student_id)
VALUES (5, 2);
INSERT INTO orders (book_id, student_id)
VALUES (4, 1);
INSERT INTO orders (book_id, student_id)
VALUES (2, 2);
