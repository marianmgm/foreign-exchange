create table exchange_rate
(
    exchange_rate_id int auto_increment
        primary key,
    source_currency  varchar(3)     not null,
    target_currency  varchar(3)     not null,
    rate             decimal(10, 6) not null,
    time_stamp       datetime       not null
);

create table transaction
(
    transaction_id  int auto_increment
        primary key,
    source_currency varchar(3)     not null,
    source_amount   decimal(10, 2) not null,
    target_currency varchar(3)     not null,
    target_amount   decimal(10, 2) not null,
    time_stamp      datetime       not null
);