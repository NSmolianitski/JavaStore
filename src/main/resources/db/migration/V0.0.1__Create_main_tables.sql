create table Users(
    id          bigint          generated always as identity primary key,
    username    varchar(30)     not null unique,
    password    varchar(256)    not null
);

create table Roles(
    id      bigint          generated always as identity primary key,
    name    varchar(10)     not null unique
);

create table Users_Roles(
    user_id bigint,
    role_id bigint,
    
    primary key (user_id, role_id),
    foreign key (user_id) references Users(id),
    foreign key (role_id) references Roles(id)
)