--liquibase formatted sql
--changeset <postgres>:<create-car-owners-orders-table>
create table if not exists car_owners_orders
(
    car_owner_id bigint not null
    constraint fk6y1bha5n50fw4xbb3ya4xmd75
    references car_owners,
    orders_id    bigint not null
    constraint uk_5r0lx2k325b9rden9ijyxdfq8
    unique
    constraint fkf92qllhtgpfjvp856tche7ow6
    references orders
);

alter table car_owners_orders
    owner to postgres;

--rollback DROP TABLE car_owners_orders;
