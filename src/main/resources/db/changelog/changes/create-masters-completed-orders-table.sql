--liquibase formatted sql
--changeset <postgres>:<create-masters-completed-orders-table>
create table if not exists masters_completed_orders
(
    master_id          bigint not null
    constraint fkjxp2x9of5tc4smolrabx78lwf
    references masters,
    completed_order_id bigint not null
    constraint uk_kvfmylu5py6nwaxsaomeo9pl3
    unique
    constraint fkpfde3tvro5je0k5uixpr4iaq4
    references orders
);

alter table masters_completed_orders
    owner to postgres;

--rollback DROP TABLE masters_completed_orders;
