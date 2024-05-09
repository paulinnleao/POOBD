package com.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
@Data
@AllArgsConstructor
public class PessoaDTO extends RepresentationModel<PessoaDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpf_pessoa;
    private String nome;
    private String endereco;
    private Integer telefone;
    private String sexo;
    private String e_mail;
}
