alter table users drop constraint FKdv26y3bb4vdmsr89c9ppnx85w;
alter table users add constraint fk_user_cart_id foreign key (cart_id) references carts (id);

alter table orders drop constraint FK594fgx8wpklcf3t41jq3grhlh;
alter table orders add constraint fk_order_cart_id foreign key (cart_id) references carts (id);

alter table treatment_cart drop constraint FKr1orrtedfp5y73ymbkquqn4hg;
alter table treatment_cart add constraint fk_treatment_cart_treatment_id foreign key (treatment_id) references treatments (id);

alter table treatment_cart drop constraint FKsob9hcn60l0m5p1gujfxsuw5e;
alter table treatment_cart add constraint fk_treatment_cart_cart_id foreign key (cart_id) references carts (id);

alter table order_user drop constraint FK4rr5n6sfje9w1em7ynp8slrow;
alter table order_user add constraint fk_order_user_user_id foreign key (user_id) references users (id);

alter table order_user drop constraint FKm7muior2ynl30n8qh9yqembso;
alter table order_user add constraint fk_order_user_order_id foreign key (order_id) references orders (id);

alter table cart_user drop constraint FK7i2mrarb8tdwxlpg12h8bhw7b;
alter table cart_user add constraint fk_cart_user_cart_id foreign key (cart_id) references carts (id);

alter table cart_user drop constraint FKqya4aa7ab1jpi9mag9u40m65f;
alter table cart_user add constraint fk_cart_user_user_id foreign key (user_id) references users (id);

alter table carts add constraint fk_carts_user_id foreign key (user_id) references users (id);
