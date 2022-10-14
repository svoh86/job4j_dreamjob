CREATE TABLE post (
    id serial primary key,
    name text,
    description text,
    created timestamp,
    visible boolean,
    city_id integer
);

CREATE TABLE candidate (
    id serial primary key,
    name text,
    description text,
    created timestamp,
    photo bytea
);