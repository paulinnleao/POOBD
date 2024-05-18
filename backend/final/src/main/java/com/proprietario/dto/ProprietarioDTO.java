package com.proprietario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ProprietarioDTO extends RepresentationModel<ProprietarioDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpfProp;
    private String cnhProp;
    private Integer bancoProp;
    private Integer agenciaProp;
    private Integer contaProp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProprietarioDTO that = (ProprietarioDTO) o;
        return Objects.equals(cpfProp, that.cpfProp) && Objects.equals(cnhProp, that.cnhProp) && Objects.equals(bancoProp, that.bancoProp) && Objects.equals(agenciaProp, that.agenciaProp) && Objects.equals(contaProp, that.contaProp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpfProp, cnhProp, bancoProp, agenciaProp, contaProp);
    }

    public Long getCpfProp() {
        return cpfProp;
    }

    public void setCpfProp(Long cpfProp) {
        this.cpfProp = cpfProp;
    }

    public String getCnhProp() {
        return cnhProp;
    }

    public void setCnhProp(String cnhProp) {
        this.cnhProp = cnhProp;
    }

    public Integer getBancoProp() {
        return bancoProp;
    }

    public void setBancoProp(Integer bancoProp) {
        this.bancoProp = bancoProp;
    }

    public Integer getAgenciaProp() {
        return agenciaProp;
    }

    public void setAgenciaProp(Integer agenciaProp) {
        this.agenciaProp = agenciaProp;
    }

    public Integer getContaProp() {
        return contaProp;
    }

    public void setContaProp(Integer contaProp) {
        this.contaProp = contaProp;
    }

    public ProprietarioDTO() {
    }
}
