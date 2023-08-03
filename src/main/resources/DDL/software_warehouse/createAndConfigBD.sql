-- Script para deletar as tabelas caso existam e recriá-las em seguida

-- Primeiro, exclua as tabelas se já existirem
DROP TABLE IF EXISTS software_warehouse.report;
DROP TABLE IF EXISTS software_warehouse.usuario;
DROP TABLE IF EXISTS software_warehouse.pessoa;

-- Em seguida, crie as tabelas novamente

CREATE TABLE software_warehouse.pessoa
(
    id_pessoa       SERIAL PRIMARY KEY,
    nome            VARCHAR(255),
    data_nascimento DATE
);

-- Renomear a tabela login para usuario

-- Recreate the usuario table with foreign key constraint
CREATE TABLE software_warehouse.usuario
(
    id_usuario      SERIAL PRIMARY KEY,
    usuario         VARCHAR(255) NOT NULL UNIQUE,
    hashsenha       VARCHAR(255),
    email           VARCHAR(255) NOT NULL UNIQUE,
    data_cadastro   DATE DEFAULT CURRENT_DATE,
    pessoa_id       INTEGER UNIQUE,
    CONSTRAINT usuario_pessoa_id_pessoa_fk
        FOREIGN KEY (pessoa_id)
            REFERENCES software_warehouse.pessoa (id_pessoa)
);

CREATE TABLE software_warehouse.report
(
    id              SERIAL PRIMARY KEY,
    nome            VARCHAR(255) UNIQUE,
    categoria       VARCHAR(255),
    link            VARCHAR(255) UNIQUE,
    data_de_criacao DATE DEFAULT CURRENT_DATE,
    descricao       TEXT,
    tipo            VARCHAR(255),
    usuario_id      INTEGER NOT NULL,
    CONSTRAINT report_usuario_id_usuario_fk
        FOREIGN KEY (usuario_id)
            REFERENCES software_warehouse.usuario (id_usuario)
            ON DELETE CASCADE
);


CREATE OR REPLACE FUNCTION update_data_de_criacao()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.data_de_criacao := CURRENT_DATE;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_data_de_criacao
    BEFORE INSERT ON software_warehouse.report
    FOR EACH ROW
EXECUTE FUNCTION update_data_de_criacao();


CREATE OR REPLACE FUNCTION update_data_cadastro()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.data_cadastro := CURRENT_DATE;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_data_cadastro
    BEFORE INSERT ON software_warehouse.usuario
    FOR EACH ROW
EXECUTE FUNCTION update_data_cadastro();

