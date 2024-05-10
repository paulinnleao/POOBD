package com.motorista;

import com.motorista_veiculo.MotoristaVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@Table(name = "MOTORISTA")
public class Motorista implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_MOTORISTA")
    private Long cpf_motorista;
    @Column(name = "CNH")
    private String cnh;
    @Column(name = "BANCO_MOT")
    private Integer banco_mot;
    @Column(name = "AGENCIA_MOT")
    private Integer agencia_mot;
    @Column(name = "CONTA_MOT")
    private Integer conta_mot;
    @OneToMany(mappedBy = "motorista")
    private List<MotoristaVeiculo> listaMotoristasVeiculos;

    public Motorista() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motorista motorista = (Motorista) o;
        return Objects.equals(cpf_motorista, motorista.cpf_motorista) && Objects.equals(cnh, motorista.cnh) && Objects.equals(banco_mot, motorista.banco_mot) && Objects.equals(agencia_mot, motorista.agencia_mot) && Objects.equals(conta_mot, motorista.conta_mot) && Objects.equals(listaMotoristasVeiculos, motorista.listaMotoristasVeiculos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf_motorista, cnh, banco_mot, agencia_mot, conta_mot, listaMotoristasVeiculos);
    }

    public List<MotoristaVeiculo> getListaMotoristasVeiculos() {
        return listaMotoristasVeiculos;
    }

    public void setListaMotoristasVeiculos(List<MotoristaVeiculo> listaMotoristasVeiculos) {
        this.listaMotoristasVeiculos = listaMotoristasVeiculos;
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
