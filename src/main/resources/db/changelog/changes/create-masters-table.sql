--liquibase formatted sql
--changeset <postgres>:<create-masters-table>
create table if not exists masters
(
    id        bigserial
    primary key,
    full_name varchar(255)
    );

alter table masters
    owner to postgres;

--rollback DROP TABLE masters;
