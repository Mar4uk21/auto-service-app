--liquibase formatted sql
--changeset <postgres>:<create-car-owners-table>
create table if not exists car_owners
(
    id bigserial
    primary key
);

alter table car_owners
    owner to postgres;

--rollback DROP TABLE car_owners;
