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

    private String placa;
    private String marca;
    private String modelo;
    private String anoFabric;
    private Integer capacidadePass;
    private String cor;
    private String tipoCombust;
    private Integer potenciaMotor;

    public VeiculoDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VeiculoDTO that = (VeiculoDTO) o;
        return Objects.equals(placa, that.placa) && Objects.equals(marca, that.marca) && Objects.equals(modelo, that.modelo) && Objects.equals(anoFabric, that.anoFabric) && Objects.equals(capacidadePass, that.capacidadePass) && Objects.equals(cor, that.cor) && Objects.equals(tipoCombust, that.tipoCombust) && Objects.equals(potenciaMotor, that.potenciaMotor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), placa, marca, modelo, anoFabric, capacidadePass, cor, tipoCombust, potenciaMotor);
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

    public String getAnoFabric() {
        return anoFabric;
    }

    public void setAnoFabric(String anoFabric) {
        this.anoFabric = anoFabric;
    }

    public Integer getCapacidadePass() {
        return capacidadePass;
    }

    public void setCapacidadePass(Integer capacidadePass) {
        this.capacidadePass = capacidadePass;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipoCombust() {
        return tipoCombust;
    }

    public void setTipoCombust(String tipoCombust) {
        this.tipoCombust = tipoCombust;
    }

    public Integer getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(Integer potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }
}
