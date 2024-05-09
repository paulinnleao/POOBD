package com.motorista.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class MotoristaDTO extends RepresentationModel<MotoristaDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpf_motorista;
    private String cnh;
    private Integer banco_mot;
    private Integer agencia_mot;
    private Integer conta_mot;
}
