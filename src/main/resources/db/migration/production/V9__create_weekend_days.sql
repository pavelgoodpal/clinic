create table weekend_days
(
    id        bigint       not null
        primary key,
    date      date         null,
    name      varchar(255) null,
    doctor_id bigint       null,
    constraint fk_weekend_day_doctor_id
        foreign key (doctor_id) references users (id)
);