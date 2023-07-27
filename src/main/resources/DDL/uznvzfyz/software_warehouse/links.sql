create table if not exists software_warehouse.links
(
    id              serial
        primary key,
    nome            varchar(255),
    categoria       varchar(255),
    link            varchar(255),
    data_de_criacao date,
    decricao        varchar(255),
    tipo            varchar(255)
);

