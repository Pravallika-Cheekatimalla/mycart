Drop table IF EXISTS users;

create table users
(
    id integer not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    age integer,
    email varchar(255) not null,
    primary key(id)
);


insert into users (id, first_name, last_name, age, email)
values (12345, 'fName1', 'lName1', 12, 'test1@mail.com');

insert into users (id, first_name, last_name, age, email)
values (12346, 'fName2', 'lName2', 13, 'test2@mail.com');

insert into users (id, first_name, last_name, age, email)
values (12347, 'fName3', 'lName3', 14, 'test3@mail.com');

insert into users (id, first_name, last_name, age, email)
values (12348, 'fName3', 'lName4', 14, 'test4@mail.com');