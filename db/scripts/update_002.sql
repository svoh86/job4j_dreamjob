CREATE TABLE IF NOT EXISTS users (
    id serial primary key,
    email varchar(255) unique,
    password text
);