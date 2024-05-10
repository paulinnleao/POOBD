-- CREATE TABLE IF NOT EXISTS VEICULOS (
--     PLACA CHAR(7) NOT NULL PRIMARY KEY CHECK (PLACA ~ '^[A-Z]{3}\d{1}[A-Z]{1}\d{2}$'),
--     MARCA VARCHAR(30) NOT NULL,
--     MODELO VARCHAR(30) NOT NULL,
--     ANO_FABRIC INTEGER NOT NULL,
--     CAPACIDADE_PASS INTEGER NOT NULL,
--     COR VARCHAR(30) NOT NULL,
--     TIPO_COMBUST CHAR(1) NOT NULL CHECK (TIPO_COMBUST IN ('G', 'A', 'D', 'F')),
--     POTENCIA_MOTOR INTEGER
-- );
CREATE TABLE IF NOT EXISTS VEICULOS (
    ID_VEICULO BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    PLACA CHAR(7) NOT NULL CHECK (PLACA ~ '^[A-Z]{3}\d{1}[A-Z]{1}\d{2}$'),
    MARCA VARCHAR(30) NOT NULL,
    MODELO VARCHAR(30) NOT NULL,
    ANO_FABRIC INTEGER NOT NULL,
    CAPACIDADE_PASS INTEGER NOT NULL,
    COR VARCHAR(30) NOT NULL,
    TIPO_COMBUST CHAR(1) NOT NULL CHECK (TIPO_COMBUST IN ('G', 'A', 'D', 'F')),
    POTENCIA_MOTOR INTEGER,
    ID_PROPRIETARIO BIGINT NOT NULL REFERENCES PROPRIETARIOS(ID_PROPRIETARIO)
    );
