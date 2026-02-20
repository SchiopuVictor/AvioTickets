create table Flight
(
    id             bigint auto_increment
        primary key,
    flight_number  varchar(100) not null,
    from_city      varchar(100) not null,
    to_city        varchar(100) not null,
    departure_time time         not null,
    gate           varchar(50)  not null,
    terminal       varchar(50)  not null
);

