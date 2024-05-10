package com.tipo_pgto;

import com.viagem.Viagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@Table(name = "TIPOS_PGTOS")
public class TipoPgto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_PAGTO")
    private Integer cod_pagto;
    @Column(name = "DESC_PAGTO")
    private String desc_pagto;
    @OneToMany(mappedBy = "tipoPgto")
    private Viagem viagem;

    public TipoPgto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoPgto tipoPgto = (TipoPgto) o;
        return Objects.equals(cod_pagto, tipoPgto.cod_pagto) && Objects.equals(desc_pagto, tipoPgto.desc_pagto) && Objects.equals(viagem, tipoPgto.viagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod_pagto, desc_pagto, viagem);
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
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
