package com.tipo_pgto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class TipoPgtoDTO  extends RepresentationModel<TipoPgtoDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer cod_pagto;
    private String desc_pagto;


}
