package com.veiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class VeiculosDTO extends RepresentationModel<VeiculosDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String placa;
    private String marca;
    private String modelo;
    private String ano_fabric;
    private Integer capacidade_pass;
    private String cor;
    private String tipo_combust;
    private Integer potencia_motor;

    public VeiculosDTO() {
    }
}
