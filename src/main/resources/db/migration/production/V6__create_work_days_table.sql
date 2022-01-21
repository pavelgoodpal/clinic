create table work_days
(
    id                 bigint       not null
        primary key,
    day_of_week varchar(40) null,
    start_at datetime(6) null,
    finish_at datetime(6) null
)