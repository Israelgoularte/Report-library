create table if not exists software_warehouse.links
(
    id              serial
        primary key,
    nome            varchar(255)
        constraint links_pk
            unique,
    categoria       varchar(255),
    link            varchar(255)
        constraint links_pk2
            unique,
    data_de_criacao date,
    descricao       text,
    tipo            varchar(255),
    login_id        integer not null
        constraint links_login_id_login_fk
            references software_warehouse.login
            on delete cascade
);

