create table if not exists software_warehouse.login
(
    id_login      serial
        primary key,
    usuario       varchar(255) not null
        unique,
    hashsenha     varchar(255),
    email         varchar(255) not null
        unique,
    data_cadastro timestamp(6) default CURRENT_TIMESTAMP,
    pessoa_id     integer
        constraint login_pk
            unique
        constraint login_pessoa_id_pessoa_fk
            references software_warehouse.pessoa
);

