package com.motorista.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class MotoristaDTO extends RepresentationModel<MotoristaDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpf_motorista;
    private String cnh;
    private Integer banco_mot;
    private Integer agencia_mot;
    private Integer conta_mot;

    public MotoristaDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MotoristaDTO that = (MotoristaDTO) o;
        return Objects.equals(cpf_motorista, that.cpf_motorista) && Objects.equals(cnh, that.cnh) && Objects.equals(banco_mot, that.banco_mot) && Objects.equals(agencia_mot, that.agencia_mot) && Objects.equals(conta_mot, that.conta_mot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpf_motorista, cnh, banco_mot, agencia_mot, conta_mot);
    }

    public Long getCpf_motorista() {
        return cpf_motorista;
    }

    public void setCpf_motorista(Long cpf_motorista) {
        this.cpf_motorista = cpf_motorista;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Integer getBanco_mot() {
        return banco_mot;
    }

    public void setBanco_mot(Integer banco_mot) {
        this.banco_mot = banco_mot;
    }

    public Integer getAgencia_mot() {
        return agencia_mot;
    }

    public void setAgencia_mot(Integer agencia_mot) {
        this.agencia_mot = agencia_mot;
    }

    public Integer getConta_mot() {
        return conta_mot;
    }

    public void setConta_mot(Integer conta_mot) {
        this.conta_mot = conta_mot;
    }
}
