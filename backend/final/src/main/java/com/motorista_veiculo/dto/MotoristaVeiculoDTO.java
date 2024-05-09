package com.motorista_veiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class MotoristaVeiculoDTO extends RepresentationModel<MotoristaVeiculoDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpf_motorista;
    private String placa_veiculo;
}
