package com.veiculo;

import com.motorista_veiculo.MotoristaVeiculo;
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
    private String ano_fabric;

    @Column(name = "CAPACIDADE_PASS")
    private Integer capacidade_pass;

    @Column(name = "COR")
    private String cor;

    @Column(name = "TIPO_COMBUST")
    private String tipo_combust;

    @Column(name = "POTENCIA_MOTOR")
    private Integer potencia_motor;

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
        return Objects.equals(placa, veiculo.placa) && Objects.equals(marca, veiculo.marca) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(ano_fabric, veiculo.ano_fabric) && Objects.equals(capacidade_pass, veiculo.capacidade_pass) && Objects.equals(cor, veiculo.cor) && Objects.equals(tipo_combust, veiculo.tipo_combust) && Objects.equals(potencia_motor, veiculo.potencia_motor) && Objects.equals(proprietario, veiculo.proprietario) && Objects.equals(listaMotoristaVeiculo, veiculo.listaMotoristaVeiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa, marca, modelo, ano_fabric, capacidade_pass, cor, tipo_combust, potencia_motor, proprietario, listaMotoristaVeiculo);
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
