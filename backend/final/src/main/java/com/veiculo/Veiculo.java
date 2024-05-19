package com.veiculo;

import com.motoristaVeiculo.MotoristaVeiculo;
import com.proprietario.Proprietario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "VEICULOS")
public class Veiculo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PLACA")
    private String placa;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "ANO_FABRIC")
    private String anoFabric;

    @Column(name = "CAPACIDADE_PASS")
    private Integer capacidadePass;

    @Column(name = "COR")
    private String cor;

    @Column(name = "TIPO_COMBUST")
    private String tipoCombust;

    @Column(name = "POTENCIA_MOTOR")
    private Integer potenciaMotor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPF_PROP")
    private Proprietario proprietario;

    @OneToMany(mappedBy = "veiculo")
    private List<MotoristaVeiculo> listaMotoristaVeiculo;

    public Veiculo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(placa, veiculo.placa) && Objects.equals(marca, veiculo.marca) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(anoFabric, veiculo.anoFabric) && Objects.equals(capacidadePass, veiculo.capacidadePass) && Objects.equals(cor, veiculo.cor) && Objects.equals(tipoCombust, veiculo.tipoCombust) && Objects.equals(potenciaMotor, veiculo.potenciaMotor) && Objects.equals(proprietario, veiculo.proprietario) && Objects.equals(listaMotoristaVeiculo, veiculo.listaMotoristaVeiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa, marca, modelo, anoFabric, capacidadePass, cor, tipoCombust, potenciaMotor, proprietario, listaMotoristaVeiculo);
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public List<MotoristaVeiculo> getListaMotoristaVeiculo() {
        return listaMotoristaVeiculo;
    }

    public void setListaMotoristaVeiculo(List<MotoristaVeiculo> listaMotoristaVeiculo) {
        this.listaMotoristaVeiculo = listaMotoristaVeiculo;
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
