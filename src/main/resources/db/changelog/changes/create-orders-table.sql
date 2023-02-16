--liquibase formatted sql
--changeset <postgres>:<create-orders-table>
create table if not exists orders
(
    id             bigserial
    primary key,
    data_order     timestamp,
    description    varchar(255),
    order_status   varchar(255),
    time_to_finish timestamp,
    total_price    numeric(19, 2),
    car_id         bigint
    constraint fkd2p23ixwrrt395glgi9nnbj23
    references cars
    );

alter table orders
    owner to postgres;

--rollback DROP TABLE orders;
