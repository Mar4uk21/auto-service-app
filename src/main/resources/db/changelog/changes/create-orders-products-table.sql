--liquibase formatted sql
--changeset <postgres>:<create-orders-products-table>
create table if not exists orders_products
(
    order_id   bigint not null
    constraint fke4y1sseio787e4o5hrml7omt5
    references orders,
    product_id bigint not null
    constraint fk43vke5jd6eyasd92t3k24kdxq
    references products
);

alter table orders_products
    owner to postgres;

--rollback DROP TABLE orders_products;
