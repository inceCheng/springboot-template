-- Create the database if it does not already exist
CREATE DATABASE IF NOT EXISTS my_db;

-- Use the newly created or existing database
USE my_db;

-- Create the 'users' table if it does not already exist
create table if not exists users
(
    id                     bigint auto_increment comment 'Unique identifier for the user'
        primary key,
    username               varchar(50)                not null comment 'User''s chosen username',
    password               varchar(100)               not null comment 'Hashed password for the user',
    email                  varchar(100)               null comment 'User''s email address',
    phone                  varchar(20)                null comment 'User''s phone number',
    address                text                       null comment 'User''s physical address',
    avatar                 varchar(255)               null comment 'URL or path to the user''s avatar image',
    last_login_ip          varchar(45)                null comment 'IP address from which the user last logged in',
    last_login_time        datetime                   null comment 'Timestamp of the user''s last login',
    status                 int         default 0      not null comment 'User''s account status: 0=inactive, 1=active, 2=frozen, 3=blocked',
    create_time            datetime                   null comment 'Timestamp when the user account was created',
    update_time            datetime                   null comment '最后更新时间',
    additional_info        json                       null comment 'Additional flexible information about the user',
    bio                    text                       null comment '个人简介',
    id_card                varchar(18)                null comment '身份证号码',
    last_login_ip_location varchar(100)               null comment 'ip归属地',
    role                   int         default 1      not null comment '用户角色(1=普通用户, 2= 管理员, 0= 系统管理员)',
    display_name           varchar(50) default 'momo' not null comment '用户昵称',
    constraint username
        unique (username),
    constraint users_id_card_uindex
        unique (id_card) comment '身份证号码唯一索引'
)
    comment 'Table to store user account information';

create index idx_email
    on users (email);

create index idx_phone
    on users (phone);

create index idx_status
    on users (status);

create index idx_username
    on users (username);



