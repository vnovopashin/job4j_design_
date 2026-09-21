CREATE TABLE mobile_operators(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE frequencies(
	id SERIAL PRIMARY KEY,
	frequency_range VARCHAR(50)
);

CREATE TABLE mobile_operators_frequencies(
	id SERIAL PRIMARY KEY,
	mobile_operator_id INT REFERENCES mobile_operators(id) UNIQUE,
	frequencies_id INT REFERENCES frequencies(id) UNIQUE
);
