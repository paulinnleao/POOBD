--ALTER DATABASE 'cmp1611_paulo_artur' RENAME TO 'cmp1611_paulo_artur_marcel';
INSERT INTO VIAGENS (
    CPF_PASSAG, CPF_MOTORISTA, PLACA, LOCAL_ORIG_VIAG, LOCAL_DEST_VIAG, DT_HORA_INICIO, DT_HORA_FIM, QTDE_PASS, FORMA_PAGTO, VALOR_PAGTO, CANCELAM_MOT, CANCELAM_PASS, COD_PAGTO
)
VALUES
(12345678901, 12345678901, 'ABC1D23', 'Centro', 'Jardim Paulista', '2024-01-03 21:00:00', '2024-01-03 21:10:00', 1, 'DINHEIRO', 150.00, 'N', 'N', 1),
(12345678901, 12345678901, 'ABC1D23', 'Centro', 'Jardim Paulista', '2024-01-03 21:01:00', '2024-01-03 21:13:00', 1, 'DINHEIRO', 150.00, 'N', 'N', 1),
(12345678901, 12345678901, 'ABC1D23', 'Centro', 'Jardim Paulista', '2024-01-03 21:02:00', '2024-01-03 23:13:00', 1, 'DINHEIRO', 150.00, 'N', 'N', 1);
