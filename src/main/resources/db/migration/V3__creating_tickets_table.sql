create table Ticket
(
    id           bigint auto_increment
        primary key,
    seat_number  varchar(50)    not null,
    price        decimal(10, 2) not null,
    booking_date time           not null,
    passenger_id bigint         not null,
    flight_id    bigint         not null,
    constraint Ticket_flight_id_fk
        foreign key (flight_id) references flight (id),
    constraint Ticket_passenger_id_fk
        foreign key (passenger_id) references passenger (id)
);

