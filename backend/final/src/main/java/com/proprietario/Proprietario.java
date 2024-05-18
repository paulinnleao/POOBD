package com.proprietario;

import com.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "PROPRIETARIOS")
public class Proprietario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_PROP")
    private Long cpfProp;

    @Column(name = "CNH_PROP")
    private String cnhProp;

    @Column(name = "BANCO_PROP")
    private Integer bancoProp;

    @Column(name = "AGENCIA_PROP")
    private Integer agenciaProp;

    @Column(name = "CONTA_PROP")
    private Integer contaProp;

    @OneToMany(mappedBy = "proprietario")
    private List<Veiculo> listaVeiculos;

    public Proprietario() {
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public Long getCpfProp() {
        return cpfProp;
    }

    public void setCpfProp(Long cpfProp) {
        this.cpfProp = cpfProp;
    }

    public String getCnhProp() {
        return cnhProp;
    }

    public void setCnhProp(String cnhProp) {
        this.cnhProp = cnhProp;
    }

    public Integer getBancoProp() {
        return bancoProp;
    }

    public void setBancoProp(Integer bancoProp) {
        this.bancoProp = bancoProp;
    }

    public Integer getAgenciaProp() {
        return agenciaProp;
    }

    public void setAgenciaProp(Integer agenciaProp) {
        this.agenciaProp = agenciaProp;
    }

    public Integer getContaProp() {
        return contaProp;
    }

    public void setContaProp(Integer contaProp) {
        this.contaProp = contaProp;
    }
}
