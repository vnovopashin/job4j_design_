CREATE TABLE operators(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE abonents(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE operators_abonents(
	id SERIAL PRIMARY KEY,
	operators_id INT REFERENCES operators(id),
	abonents_is INT REFERENCES abonents(id)
);
