create table work_week_work_day
(
    work_week_id bigint not null primary key,
    work_day_id bigint null,
    constraint fk_work_week_work_day_work_week_id
        foreign key (work_week_id) references work_weeks(id),
    constraint fk_work_week_work_day_work_day_id
        foreign key (work_day_id) references work_days(id)
);