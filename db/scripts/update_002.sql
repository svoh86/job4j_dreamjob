CREATE TABLE IF NOT EXISTS users (
    id serial primary key,
    email text unique,
    password text
);