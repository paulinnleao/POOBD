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

    private Long id_proprietario;
    private Long cpf_prop;
    private String cnh_prop;
    private Integer banco_prop;
    private Integer agencia_prop;
    private Integer conta_prop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProprietarioDTO that = (ProprietarioDTO) o;
        return Objects.equals(id_proprietario, that.id_proprietario) && Objects.equals(cpf_prop, that.cpf_prop) && Objects.equals(cnh_prop, that.cnh_prop) && Objects.equals(banco_prop, that.banco_prop) && Objects.equals(agencia_prop, that.agencia_prop) && Objects.equals(conta_prop, that.conta_prop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_proprietario, cpf_prop, cnh_prop, banco_prop, agencia_prop, conta_prop);
    }

    public Long getId_proprietario() {
        return id_proprietario;
    }

    public void setId_proprietario(Long id_proprietario) {
        this.id_proprietario = id_proprietario;
    }

    public Long getCpf_prop() {
        return cpf_prop;
    }

    public void setCpf_prop(Long cpf_prop) {
        this.cpf_prop = cpf_prop;
    }

    public String getCnh_prop() {
        return cnh_prop;
    }

    public void setCnh_prop(String cnh_prop) {
        this.cnh_prop = cnh_prop;
    }

    public Integer getBanco_prop() {
        return banco_prop;
    }

    public void setBanco_prop(Integer banco_prop) {
        this.banco_prop = banco_prop;
    }

    public Integer getAgencia_prop() {
        return agencia_prop;
    }

    public void setAgencia_prop(Integer agencia_prop) {
        this.agencia_prop = agencia_prop;
    }

    public Integer getConta_prop() {
        return conta_prop;
    }

    public void setConta_prop(Integer conta_prop) {
        this.conta_prop = conta_prop;
    }

    public ProprietarioDTO() {
    }
}
