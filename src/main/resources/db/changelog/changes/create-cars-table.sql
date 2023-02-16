--liquibase formatted sql
--changeset <postgres>:<create-cars-table>
create table if not exists cars
(
    id              bigserial
    primary key,
    manufacturer    varchar(255),
    model           varchar(255),
    number_of_car   varchar(255),
    year_of_release bigint,
    owner_id        bigint
    constraint fkoeqwfkm3sp26dhfekdbmysece
    references car_owners
    );

alter table cars
    owner to postgres;

--rollback DROP TABLE cars;
