package com.tipo_pgto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class TipoPgtoDTO  extends RepresentationModel<TipoPgtoDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id_tipo_pgto;
    private Integer cod_pagto;
    private String desc_pagto;

    public TipoPgtoDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TipoPgtoDTO that = (TipoPgtoDTO) o;
        return Objects.equals(id_tipo_pgto, that.id_tipo_pgto) && Objects.equals(cod_pagto, that.cod_pagto) && Objects.equals(desc_pagto, that.desc_pagto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_tipo_pgto, cod_pagto, desc_pagto);
    }

    public Long getId_tipo_pgto() {
        return id_tipo_pgto;
    }

    public void setId_tipo_pgto(Long id_tipo_pgto) {
        this.id_tipo_pgto = id_tipo_pgto;
    }

    public Integer getCod_pagto() {
        return cod_pagto;
    }

    public void setCod_pagto(Integer cod_pagto) {
        this.cod_pagto = cod_pagto;
    }

    public String getDesc_pagto() {
        return desc_pagto;
    }

    public void setDesc_pagto(String desc_pagto) {
        this.desc_pagto = desc_pagto;
    }
}
