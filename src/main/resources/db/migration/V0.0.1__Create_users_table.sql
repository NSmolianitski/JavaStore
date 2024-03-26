create table Users(
    id          bigint          generated always as identity,
    login       varchar(30)     not null unique,
    password    varchar(30)     not null
);