create table if not exists cryptocurrencies
(
    id     bigserial
    constraint cryptocurrencies_pk primary key,
    symbol varchar(20)    not null
    constraint cryptocurrencies_pk2 unique,
    price numeric(13,6)
    );
alter table cryptocurrencies
    owner to postgres;

create table if not exists "users"
(
    id                bigserial
    constraint users_pk
    primary key,
    name          varchar(50)    not null,
    msg_numb      bigint,
    cryptocurrency_id bigint         not null
    constraint user_cryptocurrencies_id_fk
    references cryptocurrencies,
    starting_price    numeric(13,6) not null,
    constraint users_pk2
    unique (cryptocurrency_id, name)
    );
alter table "users"
    owner to postgres;