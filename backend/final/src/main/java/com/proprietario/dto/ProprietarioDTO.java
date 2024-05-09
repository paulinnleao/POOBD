package com.proprietario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProprietarioDTO extends RepresentationModel<ProprietarioDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpf_prop;
    private String cnh_prop;
    private Integer banco_prop;
    private Integer agencia_prop;
    private Integer conta_prop;
}
