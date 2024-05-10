package com.tipo_pgto;

import com.viagem.Viagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
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
    @Column(name = "ID_TIPO_PGTO")
    private Long id_tipo_pgto;

    @Column(name = "COD_PAGTO")
    private Integer cod_pagto;

    @Column(name = "DESC_PAGTO")
    private String desc_pagto;

    @OneToMany(mappedBy = "tipoPgto")
    private List<Viagem> listaViagens;

    public TipoPgto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoPgto tipoPgto = (TipoPgto) o;
        return Objects.equals(id_tipo_pgto, tipoPgto.id_tipo_pgto) && Objects.equals(cod_pagto, tipoPgto.cod_pagto) && Objects.equals(desc_pagto, tipoPgto.desc_pagto) && Objects.equals(listaViagens, tipoPgto.listaViagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_tipo_pgto, cod_pagto, desc_pagto, listaViagens);
    }

    public Long getId_tipo_pgto() {
        return id_tipo_pgto;
    }

    public void setId_tipo_pgto(Long id_tipo_pgto) {
        this.id_tipo_pgto = id_tipo_pgto;
    }

    public List<Viagem> getListaViagens() {
        return listaViagens;
    }

    public void setListaViagens(List<Viagem> listaViagens) {
        this.listaViagens = listaViagens;
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
