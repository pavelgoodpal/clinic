create table treatment_periods
(
    id          bigint      not null
        primary key,
    finish_at   datetime(6) null,
    start_at    datetime(6) not null,
    work_day_id bigint      null,
    constraint fk_treatment_period_work_day_id
        foreign key (work_day_id) references work_days (id)
);