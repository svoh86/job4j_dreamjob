CREATE TABLE IF NOT EXISTS post (
    id serial primary key,
    name text,
    description text,
    created timestamp,
    visible boolean,
    city_id integer
);

CREATE TABLE IF NOT EXISTS candidate (
    id serial primary key,
    name text,
    description text,
    created timestamp,
    photo bytea
);