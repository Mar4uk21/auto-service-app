--liquibase formatted sql
--changeset <postgres>:<create-mechanics-completed-orders-table>
create table if not exists mechanics_completed_orders
(
    mechanic_id         bigint not null
    constraint fki6br1ek54g7tr93w5f4q1n8yi
    references mechanics,
    completed_orders_id bigint not null
    constraint uk_2ufqbiwyq6jjn8wh11mgy3qt5
    unique
    constraint fkb6qlfn4e5ggs0h6iwaihi1av4
    references orders
);

alter table mechanics_completed_orders
    owner to postgres;

--rollback DROP TABLE mechanics_completed_orders;
