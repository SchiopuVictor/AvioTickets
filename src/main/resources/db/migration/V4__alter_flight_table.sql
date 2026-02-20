alter table flight
    add base_price decimal(10, 2) not null after departure_time;