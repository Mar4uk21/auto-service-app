--liquibase formatted sql
--changeset <postgres>:<create-orders-services-table>
create table if not exists orders_services
(
    order_id    bigint not null
    constraint fkq863ndc65lt9lgj0jg1h8ravg
    references orders,
    services_id bigint not null
    constraint uk_aj2lt32lj339swj2eflcnucmp
    unique
    constraint fk9nptc0sibum2590in8vuooa7x
    references type_services
);

alter table orders_services
    owner to postgres;

--rollback DROP TABLE orders_services;
