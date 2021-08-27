
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (1, 'martin', 'martin@fastcampus.com', now(), now());

insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (2, 'dennis', 'dennis@fastcampus.com', now(), now());

insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (3, 'sophia', 'sophia@slowcampus.com', now(), now());

insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (4, 'james', 'james@slowcampus.com', now(), now());

insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (5, 'martin', 'martin@another.com', now(), now());

insert into publisher(`id`,`name`) values ( 1,'패스트캠퍼스');
insert into book(`id`,`name`,`publisher_id`, `status`) values ( 1,'jpa초격차패키지' ,1, 100);
insert into book(`id`,`name`, `publisher_id`, `status`)values (2, 'jpa 2', 1, 200);

INSERT into review(`id`,`title`,`content`,`score`,`user_id`,`book_id`) values (1,'title1', 'good', 5.0, 1,1);

INSERT into review(`id`,`title`,`content`,`score`,`user_id`,`book_id`) values (2,'title2', 'bad', 3.0, 2,2);
