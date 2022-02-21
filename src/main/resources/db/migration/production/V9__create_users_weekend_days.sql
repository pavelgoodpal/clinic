create table users_weekend_days
(
    doctor_id       bigint not null,
    weekend_days_id bigint not null,
    constraint uk_users_weekend_day_weekend_days_id
        unique (weekend_days_id),
    constraint fk_users_weekend_day_doctor_id
        foreign key (doctor_id) references users (id),
    constraint fk_users_weekend_day_weekend_days_id
        foreign key (weekend_days_id) references weekend_days (id)
);