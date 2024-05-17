-- CREATE TABLE IF NOT EXISTS PASSAGEIROS(
--     CPF_PASSAG BIGINT NOT NULL PRIMARY KEY,
--     CARTAO_CRED VARCHAR(20),
--     BANDEIRA_CARTAO VARCHAR(20),
--     CIDADE_ORIG VARCHAR(30)
-- );
CREATE TABLE IF NOT EXISTS PASSAGEIROS (
    CPF_PASSAG BIGINT PRIMARY KEY NOT NULL,
    CARTAO_CRED VARCHAR(20),
    BANDEIRA_CARTAO VARCHAR(20),
    CIDADE_ORIG VARCHAR(30)
    );
