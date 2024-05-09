package com.proprietario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "PROPRIETARIOS")
public class Proprietario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_PROP")
    private Long cpf_prop;
    @Column(name = "CNH_PROP")
    private String cnh_prop;
    @Column(name = "BANCO_PROP")
    private Integer banco_prop;
    @Column(name = "AGENCIA_PROP")
    private Integer agencia_prop;
    @Column(name = "CONTA_PROP")
    private Integer conta_prop;

    public Proprietario() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietario that = (Proprietario) o;
        return Objects.equals(cpf_prop, that.cpf_prop) && Objects.equals(cnh_prop, that.cnh_prop) && Objects.equals(banco_prop, that.banco_prop) && Objects.equals(agencia_prop, that.agencia_prop) && Objects.equals(conta_prop, that.conta_prop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf_prop, cnh_prop, banco_prop, agencia_prop, conta_prop);
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
}
