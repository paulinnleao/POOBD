package com.tipoPgto;

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
@Table(name = "TIPOS_PAGTOS")
public class TipoPgto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_PAGTO")
    private Integer codPagto;

    @Column(name = "DESC_PAGTO")
    private String descPagto;

    @OneToMany(mappedBy = "tipoPgto")
    private List<Viagem> listaViagens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoPgto tipoPgto = (TipoPgto) o;
        return Objects.equals(codPagto, tipoPgto.codPagto) && Objects.equals(descPagto, tipoPgto.descPagto) && Objects.equals(listaViagens, tipoPgto.listaViagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codPagto, descPagto, listaViagens);
    }

    public TipoPgto() {
    }

    public List<Viagem> getListaViagens() {
        return listaViagens;
    }

    public void setListaViagens(List<Viagem> listaViagens) {
        this.listaViagens = listaViagens;
    }

    public Integer getCodPagto() {
        return codPagto;
    }

    public void setCodPagto(Integer codPagto) {
        this.codPagto = codPagto;
    }

    public String getDescPagto() {
        return descPagto;
    }

    public void setDescPagto(String descPagto) {
        this.descPagto = descPagto;
    }
}
