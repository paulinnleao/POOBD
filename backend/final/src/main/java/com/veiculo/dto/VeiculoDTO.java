package com.veiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class VeiculoDTO extends RepresentationModel<VeiculoDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id_veiculo;
    private String placa;
    private String marca;
    private String modelo;
    private String ano_fabric;
    private Integer capacidade_pass;
    private String cor;
    private String tipo_combust;
    private Integer potencia_motor;

    public VeiculoDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VeiculoDTO that = (VeiculoDTO) o;
        return Objects.equals(id_veiculo, that.id_veiculo) && Objects.equals(placa, that.placa) && Objects.equals(marca, that.marca) && Objects.equals(modelo, that.modelo) && Objects.equals(ano_fabric, that.ano_fabric) && Objects.equals(capacidade_pass, that.capacidade_pass) && Objects.equals(cor, that.cor) && Objects.equals(tipo_combust, that.tipo_combust) && Objects.equals(potencia_motor, that.potencia_motor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_veiculo, placa, marca, modelo, ano_fabric, capacidade_pass, cor, tipo_combust, potencia_motor);
    }

    public Long getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Long id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno_fabric() {
        return ano_fabric;
    }

    public void setAno_fabric(String ano_fabric) {
        this.ano_fabric = ano_fabric;
    }

    public Integer getCapacidade_pass() {
        return capacidade_pass;
    }

    public void setCapacidade_pass(Integer capacidade_pass) {
        this.capacidade_pass = capacidade_pass;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo_combust() {
        return tipo_combust;
    }

    public void setTipo_combust(String tipo_combust) {
        this.tipo_combust = tipo_combust;
    }

    public Integer getPotencia_motor() {
        return potencia_motor;
    }

    public void setPotencia_motor(Integer potencia_motor) {
        this.potencia_motor = potencia_motor;
    }
}
