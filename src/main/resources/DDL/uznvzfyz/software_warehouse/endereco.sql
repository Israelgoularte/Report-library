create table if not exists software_warehouse.endereco
(
    id_endereco serial
        primary key,
    cep         varchar(10),
    rua         varchar(255),
    numero      varchar(10),
    complemento varchar(255),
    bairro      varchar(255),
    cidade      varchar(255),
    estado      varchar(255),
    pais        varchar(255)
);

