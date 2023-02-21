--liquibase formatted sql
--changeset <postgres>:<create-masters-completed-orders-table>
create table if not exists masters_completed_orders
(
    master_id           bigint not null
    constraint fkjxp2x9of5tc4smolrabx78lwf
    references masters,
    completed_orders_id bigint not null
    constraint uk_16w3uq9591nrn9fq878e3nt95
    unique
    constraint fk4icsu52vm1l2scqj0sroq3lcy
    references orders
);

alter table masters_completed_orders
    owner to postgres;

--rollback DROP TABLE masters_completed_orders;
