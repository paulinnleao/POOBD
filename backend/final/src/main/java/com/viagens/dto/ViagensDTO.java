package com.viagens.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class ViagensDTO extends RepresentationModel<ViagensDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id_viagem;
    private Long cpf_pass_viag;
    private Long cpf_mot_viag;
    private String placa_veic_viag;
    private String local_orig_viag;
    private String local_dest_viag;
    private Date dt_hora_inicio;
    private Date dt_hora_fim;
    private Integer qtde_pass;
    private String forma_pagto;
    private Double valor_pagto;
    private String cancelam_mot;
    private String cancelam_pass;
}
