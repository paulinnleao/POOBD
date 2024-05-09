package com.motorista_veiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@Table(name = "MOTORISTAS_VEICULOS")
public class MotoristaVeiculo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_MOTORISTA")
    private Long cpf_motorista;
    @Column(name = "PLACA_VEICULO")
    private String placa_veiculo;

    public MotoristaVeiculo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotoristaVeiculo that = (MotoristaVeiculo) o;
        return Objects.equals(cpf_motorista, that.cpf_motorista) && Objects.equals(placa_veiculo, that.placa_veiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf_motorista, placa_veiculo);
    }

    public Long getCpf_motorista() {
        return cpf_motorista;
    }

    public void setCpf_motorista(Long cpf_motorista) {
        this.cpf_motorista = cpf_motorista;
    }

    public String getPlaca_veiculo() {
        return placa_veiculo;
    }

    public void setPlaca_veiculo(String placa_veiculo) {
        this.placa_veiculo = placa_veiculo;
    }
}
