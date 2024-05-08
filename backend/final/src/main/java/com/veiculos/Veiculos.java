package com.veiculos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Table(name = "VEICULO")
public class Veiculos implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PLACA")
    private String placa;
    @Column(name = "MARCA")
    private String marca;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "ANO_FABRIC")
    private String ano_fabric;
    @Column(name = "CAPACIDADE_PASS")
    private Integer capacidade_pass;
    @Column(name = "COR")
    private String cor;
    @Column(name = "TIPO_COMBUST")
    private String tipo_combust;
    @Column(name = "POTENCIA_MOTOR")
    private Integer potencia_motor;


}
