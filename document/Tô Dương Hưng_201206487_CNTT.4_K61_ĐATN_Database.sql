create table collaborator
(
    collaborator_id varchar(255)                 not null
        primary key,
    Image           varchar(255)                 null,
    Username        varchar(256) charset utf8mb3 not null,
    Email           varchar(255)                 null,
    Phone           varchar(255)                 not null,
    Name            varchar(256) charset utf8mb3 not null,
    Address         varchar(255)                 not null,
    Description     varchar(10000)               null,
    Status          int                          not null,
    Field           varchar(255) charset utf8mb3 null,
    time_end        varchar(255)                 null,
    time_start      varchar(255)                 null,
    password        varchar(255)                 null,
    time            varchar(255)                 null
);

create table collaborator_account
(
    collaborator_account_id varchar(255) not null
        primary key,
    password                varchar(255) not null,
    username                varchar(255) not null,
    collaborator_id         varchar(255) not null,
    constraint FKpf8rfybf4wtbreugnupamcil7
        foreign key (collaborator_id) references collaborator (collaborator_id)
);

create table customer
(
    customer_id     varchar(255) not null,
    Address         varchar(255) not null,
    Phone           varchar(255) not null,
    Name            varchar(255) not null,
    Email           varchar(255) null,
    collaborator_id varchar(255) not null,
    id              varchar(255) not null
        primary key,
    time            varchar(255) null,
    constraint customer_ibfk_1
        foreign key (collaborator_id) references collaborator (collaborator_id)
);

create table employee
(
    employee_id     varchar(255) not null
        primary key,
    Name            varchar(255) not null,
    Phone           varchar(255) not null,
    status          varchar(255) not null,
    collaborator_id varchar(255) not null,
    constraint employee_ibfk_1
        foreign key (collaborator_id) references collaborator (collaborator_id)
);

create table field
(
    field_id        int          not null
        primary key,
    field_name      varchar(255) not null,
    collaborator_id varchar(255) not null,
    constraint field_ibfk_1
        foreign key (collaborator_id) references collaborator (collaborator_id)
);

create table price
(
    price_id        varchar(255) not null
        primary key,
    price_high      varchar(255) null,
    price_low       varchar(255) null,
    type            varchar(255) not null,
    collaborator_id varchar(255) not null,
    name            varchar(255) not null,
    constraint FKbmiybgy41urxgnm4t3vgnjgpf
        foreign key (collaborator_id) references collaborator (collaborator_id)
);

create table user
(
    user_id  varchar(255)  not null
        primary key,
    Name     varchar(255)  not null,
    Phone    varchar(255)  not null,
    Email    varchar(255)  null,
    Address  varchar(1000) null,
    password varchar(255)  not null,
    time     varchar(255)  null
);

create table comment
(
    comment_id    varchar(255) not null
        primary key,
    content       varchar(255) not null,
    time          varchar(255) null,
    collection_id varchar(255) not null,
    user_id       varchar(255) not null,
    constraint FK2o41e4evvdi9qmqkibev4kywm
        foreign key (collection_id) references collaborator (collaborator_id),
    constraint FK8kcum44fvpupyw6f5baccx25c
        foreign key (user_id) references user (user_id)
);

create table device
(
    device_id    varchar(255) not null
        primary key,
    Name         varchar(255) not null,
    Manufacturer varchar(255) null,
    Type         varchar(255) not null,
    user_id      varchar(255) not null,
    model_name   varchar(255) null,
    constraint device_ibfk_1
        foreign key (user_id) references user (user_id)
);

create table notification
(
    notification_id int auto_increment
        primary key,
    Description     varchar(255) not null,
    status          varchar(255) null,
    from_id         varchar(255) not null,
    collaborator_id varchar(255) not null,
    user_id         varchar(255) not null,
    time            varchar(255) not null,
    constraint notification_ibfk_1
        foreign key (user_id) references user (user_id),
    constraint notification_ibfk_2
        foreign key (collaborator_id) references collaborator (collaborator_id)
);

create table rate
(
    rate_id         int auto_increment
        primary key,
    Comment         varchar(1000) null,
    Star            int           not null,
    id_reply        int           not null,
    collaborator_id varchar(255)  not null,
    user_id         varchar(255)  not null,
    constraint rate_ibfk_1
        foreign key (user_id) references user (user_id),
    constraint rate_ibfk_2
        foreign key (collaborator_id) references collaborator (collaborator_id)
);

create table request
(
    request_id        varchar(255) not null
        primary key,
    Manufacturer      varchar(255) null,
    Type              varchar(255) not null,
    error_description varchar(255) not null,
    fix_description   varchar(255) null,
    model_name        varchar(255) null,
    status_device     varchar(255) not null,
    status_request    varchar(255) not null,
    time_accept       varchar(255) null,
    time_end          varchar(255) null,
    time_start        varchar(255) null,
    collaborator_id   varchar(255) not null,
    device_id         varchar(255) null,
    employee_id       varchar(255) null,
    user_id           varchar(255) not null,
    Name              varchar(255) null,
    rate              int          null,
    constraint FK7684nk68s5p2oh2f009vq692o
        foreign key (employee_id) references employee (employee_id),
    constraint FKokwfgvxo6ld947w5wkm7w6r1y
        foreign key (device_id) references device (device_id),
    constraint request_ibfk_1
        foreign key (user_id) references user (user_id),
    constraint request_ibfk_2
        foreign key (collaborator_id) references collaborator (collaborator_id)
);

create table visit
(
    visit_id varchar(255) not null
        primary key,
    month    varchar(255) null,
    year     varchar(255) null
);

