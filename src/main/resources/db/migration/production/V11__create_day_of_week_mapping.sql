create table day_of_week_mapping
(
    work_week_id     bigint       not null,
    work_day_id           bigint       not null,
    days_of_week_key varchar(255) not null,
    primary key (work_week_id, days_of_week_key),
    constraint uk_day_of_week_mapping_work_day_id
        unique (work_day_id),
    constraint fk_day_of_week_mapping_work_day_id
        foreign key (work_day_id) references work_days (id),
    constraint fk_day_of_week_mapping_work_week_id
        foreign key (work_week_id) references work_weeks (id)
);