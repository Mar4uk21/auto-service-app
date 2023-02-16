--liquibase formatted sql
--changeset <postgres>:<create-products-table>
create table if not exists products
(
    id    bigserial
    primary key,
    price numeric(19, 2),
    title varchar(255)
    );

alter table products
    owner to postgres;

--rollback DROP TABLE products;
