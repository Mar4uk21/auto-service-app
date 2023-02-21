--liquibase formatted sql
--changeset <postgres>:<create-car-owners-orders-table>
create table if not exists car_owners_orders
(
    car_owner_id bigint not null
    constraint fk6y1bha5n50fw4xbb3ya4xmd75
    references car_owners,
    order_id     bigint not null
    constraint uk_iqo22k4nucd5cyr8ggfvcwjvn
    unique
    constraint fkqt3h29mtu233dv1kycik03g3r
    references orders
);

alter table car_owners_orders
    owner to postgres;

--rollback DROP TABLE car_owners_orders;
