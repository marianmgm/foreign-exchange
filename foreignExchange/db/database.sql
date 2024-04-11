create table exchange
(
    exchange_rate_id int auto_increment
        primary key,
    source_currency  varchar(3)     not null,
    target_currency  varchar(3)     not null,
    rate             decimal(10, 6) not null,
    time_stamp       datetime       not null
);