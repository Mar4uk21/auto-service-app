--liquibase formatted sql
--changeset <postgres>:<create-orders-products-table>
create table if not exists orders_products
(
    order_id    bigint not null
    constraint fke4y1sseio787e4o5hrml7omt5
    references orders,
    products_id bigint not null
    constraint fkqgxvu9mvqx0bv2ew776laoqvv
    references products
);

alter table orders_products
    owner to postgres;

--rollback DROP TABLE orders_products;
