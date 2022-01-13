create table outbox
(
    id bigint not null primary key,
    event_type varchar(255) null,
    payload varchar(255),
    creation_time datetime(6) null
);
