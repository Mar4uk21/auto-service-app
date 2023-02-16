--liquibase formatted sql
--changeset <postgres>:<create-type-services-table>
create table if not exists type_services
(
    id             bigserial
    primary key,
    payment_status varchar(255),
    price          numeric(19, 2),
    mechanic_id    bigint
    constraint fkstpslgb1xbh3fq8oa6ah8qc5l
    references mechanics,
    order_id       bigint
    constraint fkihm7x3r1llikoq4oge8ua8hy8
    references orders
    );

alter table type_services
    owner to postgres;



--rollback DROP TABLE type_services;
