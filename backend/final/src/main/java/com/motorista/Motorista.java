package com.motorista;

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
@Table(name = "MOTORISTA")
public class Motorista implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_MOTORISTA")
    private Long cpf_motorista;
    @Column(name = "CNH")
    private String cnh;
    @Column(name = "BANCO_MOT")
    private Integer banco_mot;
    @Column(name = "AGENCIA_MOT")
    private Integer agencia_mot;
    @Column(name = "CONTA_MOT")
    private Integer conta_mot;
}
