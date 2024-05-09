package com.passageiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class PassageiroDTO extends RepresentationModel<PassageiroDTO> implements Serializable {
    @Serial
    public static final long serialVersionUID = 1L;

    private Long cpf_passg;
    private String cartao_cred;
    private String bandeira_cartao;
    private String cidade_orig;
}
