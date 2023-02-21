--liquibase formatted sql
--changeset <postgres>:<create-orders-services-table>
create table if not exists orders_services
(
    order_id   bigint not null
    constraint fkq863ndc65lt9lgj0jg1h8ravg
    references orders,
    service_id bigint not null
    constraint uk_6stku4m0dy2cj3phyilue8998
    unique
    constraint fkdohddk6txmwlawf09fxbvq0tu
    references type_services
);

alter table orders_services
    owner to postgres;

--rollback DROP TABLE orders_services;
