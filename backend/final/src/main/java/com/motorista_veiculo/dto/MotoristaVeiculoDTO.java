package com.motorista_veiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class MotoristaVeiculoDTO extends RepresentationModel<MotoristaVeiculoDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpf_motorista;
    private String placa_veiculo;
    private Long id_motorista_veiculo;

    public MotoristaVeiculoDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MotoristaVeiculoDTO that = (MotoristaVeiculoDTO) o;
        return Objects.equals(cpf_motorista, that.cpf_motorista) && Objects.equals(placa_veiculo, that.placa_veiculo) && Objects.equals(id_motorista_veiculo, that.id_motorista_veiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpf_motorista, placa_veiculo, id_motorista_veiculo);
    }

    public Long getId_motorista_veiculo() {
        return id_motorista_veiculo;
    }

    public void setId_motorista_veiculo(Long id_motorista_veiculo) {
        this.id_motorista_veiculo = id_motorista_veiculo;
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
