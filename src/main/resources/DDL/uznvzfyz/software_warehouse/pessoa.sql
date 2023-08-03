create table if not exists software_warehouse.pessoa
(
    id_pessoa       serial
        primary key,
    nome            varchar(255),
    data_nascimento date
);

