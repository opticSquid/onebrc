CREATE TABLE IF NOT EXISTS weather_data(
	id serial primary key,
    station varchar(255),
    temp real
);