create table hibernate_sequence
(
    next_val bigint null
);

create table treatments
(
    id             bigint       not null
        primary key,
    description    varchar(255) null,
    name           varchar(255) null,
    price          bigint       not null,
    treatment_time bigint       not null
);

create table carts
(
    id                 bigint       not null
        primary key,
    total_price        bigint       null,
    creation_time      datetime(6)  null,
    status             varchar(255) null,
    last_modified_time datetime(6)  null,
    user_id            bigint       null
);

create table users
(
    id           bigint       not null
        primary key,
    email        varchar(255) null,
    first_name   varchar(255) null,
    last_name    varchar(40)  null,
    password     varchar(255) null,
    phone_number varchar(255) null,
    role         varchar(255) null,
    status       varchar(255) null,
    cart_id      bigint       null,
    constraint FKdv26y3bb4vdmsr89c9ppnx85w
        foreign key (cart_id) references carts (id)
);

alter table carts
    add foreign key (user_id) references users (id);

create table orders
(
    id                 bigint       not null
        primary key,
    additional_info    varchar(255) null,
    creation_time      datetime(6)  null,
    email              varchar(255) null,
    finish_at          datetime(6)  null,
    last_modified_date datetime(6)  null,
    phone_number       varchar(255) null,
    start_at           datetime(6)  null,
    cart_id            bigint       null,
    constraint FK594fgx8wpklcf3t41jq3grhlh
        foreign key (cart_id) references carts (id)
);

create table treatment_cart
(
    cart_id      bigint not null,
    treatment_id bigint not null,
    constraint FKr1orrtedfp5y73ymbkquqn4hg
        foreign key (treatment_id) references treatments (id),
    constraint FKsob9hcn60l0m5p1gujfxsuw5e
        foreign key (cart_id) references carts (id)
);

create table order_user
(
    user_id  bigint null,
    order_id bigint not null
        primary key,
    constraint FK4rr5n6sfje9w1em7ynp8slrow
        foreign key (user_id) references users (id),
    constraint FKm7muior2ynl30n8qh9yqembso
        foreign key (order_id) references orders (id)
);

create table cart_user
(
    user_id bigint null,
    cart_id bigint not null
        primary key,
    constraint FK7i2mrarb8tdwxlpg12h8bhw7b
        foreign key (cart_id) references carts (id),
    constraint FKqya4aa7ab1jpi9mag9u40m65f
        foreign key (user_id) references users (id)
);

insert into hibernate_sequence (next_val)
value (0);

insert into users (id, email, first_name, last_name, password, phone_number, role, status)
values (3, 'pd271828@gmail.com', 'Pavel', 'Deshevov', '$2a$12$zYALA0.9FzUETlAMcWDJ2e6rFkzA9N2stnbKb/u5mFPjBix7dj1Py',
        '89880222745', 'ADMIN', 'ACTIVE');

insert into treatments (id, description, name, price, treatment_time)
values (1, 'really cool service', 'cool service', 2000, 6);