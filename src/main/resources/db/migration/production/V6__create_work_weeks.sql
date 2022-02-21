create table work_weeks
(
    id              bigint       not null
        primary key,
    activation_code varchar(255) null,
    status          varchar(255) null,
    doctor_id       bigint       null,
    constraint fk_work_week_doctor_id
        foreign key (doctor_id) references users (id)
);