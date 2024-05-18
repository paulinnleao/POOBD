package com.motorista;

import com.motorista_veiculo.MotoristaVeiculo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@Table(name = "MOTORISTAS")
public class Motorista implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_MOTORISTA")
    private Long cpfMotorista;


    @Column(name = "CNH")
    @Size(max = 15, min = 15, message = "CNH deve conter 15 caracteres")
    private String cnh;

    @Column(name = "BANCO_MOT")
    private Integer bancoMot;

    @Column(name = "AGENCIA_MOT")
    private Integer agenciaMot;

    @Column(name = "CONTA_MOT")
    private Integer contaMot;

    @OneToMany(mappedBy = "motorista")
    private List<MotoristaVeiculo> listaMotoristasVeiculos;

    public Motorista() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motorista motorista = (Motorista) o;
        return Objects.equals(cpfMotorista, motorista.cpfMotorista) && Objects.equals(cnh, motorista.cnh) && Objects.equals(bancoMot, motorista.bancoMot) && Objects.equals(agenciaMot, motorista.agenciaMot) && Objects.equals(contaMot, motorista.contaMot) && Objects.equals(listaMotoristasVeiculos, motorista.listaMotoristasVeiculos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfMotorista, cnh, bancoMot, agenciaMot, contaMot, listaMotoristasVeiculos);
    }

    public List<MotoristaVeiculo> getListaMotoristasVeiculos() {
        return listaMotoristasVeiculos;
    }

    public void setListaMotoristasVeiculos(List<MotoristaVeiculo> listaMotoristasVeiculos) {
        this.listaMotoristasVeiculos = listaMotoristasVeiculos;
    }

    public Long getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(Long cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Integer getBancoMot() {
        return bancoMot;
    }

    public void setBancoMot(Integer bancoMot) {
        this.bancoMot = bancoMot;
    }

    public Integer getAgenciaMot() {
        return agenciaMot;
    }

    public void setAgenciaMot(Integer agenciaMot) {
        this.agenciaMot = agenciaMot;
    }

    public Integer getContaMot() {
        return contaMot;
    }

    public void setContaMot(Integer contaMot) {
        this.contaMot = contaMot;
    }
}
