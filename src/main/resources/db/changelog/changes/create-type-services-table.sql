--liquibase formatted sql
--changeset <postgres>:<create-type-services-table>
create table if not exists type_services
(
    id             bigserial
    primary key,
    payment_status varchar(255),
    price          numeric(19, 2),
    master_id      bigint
    constraint fkk3e4fpabxq7boaflqsre5j11
    references masters,
    order_id       bigint
    constraint fkihm7x3r1llikoq4oge8ua8hy8
    references orders
    );

alter table type_services
    owner to postgres;

--rollback DROP TABLE type_services;
