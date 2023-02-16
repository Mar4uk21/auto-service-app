--liquibase formatted sql
--changeset <postgres>:<create-car-owners-cars-table>
create table if not exists car_owners_cars
(
    car_owner_id bigint not null
    constraint fkpaquvhq4q7xn7ha9qw9gb07y7
    references car_owners,
    cars_id      bigint not null
    constraint uk_95xdqdtnikjoruagv0dsiaxw2
    unique
    constraint fklp4yqp054wns0jfi8m4k2kiog
    references cars
);

alter table car_owners_cars
    owner to postgres;

--rollback DROP TABLE car_owners_cars;
