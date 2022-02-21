create table work_days
(
    id             bigint       not null
        primary key,
    day_of_week    varchar(255) null,
    work_day       bit          not null,
    work_finish_at time         null,
    work_start_at  time         null
);