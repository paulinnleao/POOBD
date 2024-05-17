-- CREATE TABLE IF NOT EXISTS PESSOAS(
--     CPF_PESSOA BIGINT NOT NULL PRIMARY KEY,
--     NOME VARCHAR(50) NOT NULL,
--     ENDERECO VARCHAR(50),
--     TELEFONE INT,
--     SEXO VARCHAR(1) CHECK(SEXO IN ('M', 'F')),
--     E_MAIL VARCHAR(30)
-- );
CREATE TABLE IF NOT EXISTS PESSOAS (
    CPF_PESSOA BIGINT  PRIMARY KEY NOT NULL,
    NOME VARCHAR(50) NOT NULL,
    ENDERECO VARCHAR(50),
    TELEFONE BIGINT,
    SEXO VARCHAR(1) CHECK(SEXO IN ('M', 'F')),
    E_MAIL VARCHAR(30)
    );
