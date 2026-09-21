CREATE TABLE mobile_operators(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE phone_numbers(
	id SERIAL PRIMARY KEY,
	phone_number VARCHAR(50),
	mobile_operators_id INT REFERENCES mobile_operators(id)
);
