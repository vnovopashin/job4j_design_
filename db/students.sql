CREATE TABLE students
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO students (name)
VALUES ('Иван Иванов');
INSERT INTO students (name)
VALUES ('Петр Петров');


CREATE VIEW show_students_with_2_or_more_books
AS
SELECT s.name AS student, COUNT(a.name), a.name AS author
FROM students AS s
         JOIN orders o ON s.id = o.student_id
         JOIN books b ON o.book_id = b.id
         JOIN authors a ON b.author_id = a.id
GROUP BY (s.name, a.name)
HAVING COUNT(a.name) >= 2;

SELECT * FROM show_students_with_2_or_more_books;


