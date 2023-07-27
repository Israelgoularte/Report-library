create table if not exists software_warehouse.telefone
(
    id_telefone serial
        primary key,
    ddd         varchar(3),
    numero      varchar(20),
    comentario  varchar(255),
    id_pessoa   integer
        references software_warehouse.pessoa
);

