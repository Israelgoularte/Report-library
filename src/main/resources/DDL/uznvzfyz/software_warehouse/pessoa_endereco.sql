create table if not exists software_warehouse.pessoa_endereco
(
    id_pessoa   integer not null
        references software_warehouse.pessoa,
    id_endereco integer not null
        references software_warehouse.endereco,
    primary key (id_pessoa, id_endereco)
);

