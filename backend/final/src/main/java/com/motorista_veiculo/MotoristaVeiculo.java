package com.motorista_veiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@Table(name = "MOTORISTAS_VEICULOS")
public class MotoristaVeiculo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_MOTORISTA")
    private Long cpf_motorista;
    @Column(name = "PLACA_VEICULO")
    private String placa_veiculo;
}
