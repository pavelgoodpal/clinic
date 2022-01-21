create table work_weeks
(
    id bigint not null primary key,
    status varchar(30) null,
    doctor_id bigint null,
    constraint fk_work_week_doctor_id foreign key (doctor_id) references users(id)
);

alter table users add constraint fk_user_work_week_id
    foreign key (work_week_id) references work_weeks(id);