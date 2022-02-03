create table work_weeks
(
    id bigint not null primary key,
    status varchar(30) null,
    doctor_id bigint null,
    monday_start time null,
    monday_finish time null,
    tuesday_start time null,
    tuesday_finish time null,
    wednesday_start time null,
    wednesday_finish time null,
    thursday_start time null,
    thursday_finish time null,
    friday_start time null,
    friday_finish time null,
    saturday_start time null,
    saturday_finish time null,
    sunday_start time null,
    sunday_finish time null,
    activation_code varchar(40) null,
    constraint fk_work_week_doctor_id foreign key (doctor_id) references users(id)
);

alter table users add constraint fk_user_work_week_id
    foreign key (work_week_id) references work_weeks(id);