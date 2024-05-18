package com.tipoPgto.dto;

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

    private Integer codPagto;
    private String descPagto;

    public TipoPgtoDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TipoPgtoDTO that = (TipoPgtoDTO) o;
        return Objects.equals(codPagto, that.codPagto) && Objects.equals(descPagto, that.descPagto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), codPagto, descPagto);
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
