create table if not exists software_warehouse.aplicativo
(
    idaplicativo       serial
        primary key,
    nome               varchar(255) not null,
    data_cadastro      date         not null,
    link_icon          varchar(255) not null,
    link_background    varchar(255) not null,
    descricao          text,
    link_inicializador varchar(255) not null
);

