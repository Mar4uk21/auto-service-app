--liquibase formatted sql
--changeset <postgres>:<create-car-owners-cars-table>
create table if not exists car_owners_cars
(
    car_owner_id bigint not null
    constraint fkpaquvhq4q7xn7ha9qw9gb07y7
    references car_owners,
    car_id       bigint not null
    constraint uk_3qy61o9fwvi2k27xqe6cpfoi9
    unique
    constraint fkhibx8yx2hp4tfpv3wh4md38gy
    references cars
);

alter table car_owners_cars
    owner to postgres;

--rollback DROP TABLE car_owners_cars;
