package com.passageiro;

import com.viagem.Viagem;
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
@Table(name = "PASSAGEIROS")
public class Passageiro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_PASSAG")
    private Long cpfPassg;

    @Column(name = "CARTAO_CRED")
    private String cartaoCred;

    @Column(name = "BANDEIRA_CARTAO")
    private String bandeiraCartao;

    @Column(name = "CIDADE_ORIG")
    private String cidadeOrig;

    @OneToMany(mappedBy = "passageiro")
    private List<Viagem> listaViagens;


    public Passageiro() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passageiro that = (Passageiro) o;
        return Objects.equals(cpfPassg, that.cpfPassg) && Objects.equals(cartaoCred, that.cartaoCred) && Objects.equals(bandeiraCartao, that.bandeiraCartao) && Objects.equals(cidadeOrig, that.cidadeOrig) && Objects.equals(listaViagens, that.listaViagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfPassg, cartaoCred, bandeiraCartao, cidadeOrig, listaViagens);
    }

    public List<Viagem> getListaViagens() {
        return listaViagens;
    }

    public void setListaViagens(List<Viagem> listaViagens) {
        this.listaViagens = listaViagens;
    }

    public Long getCpfPassg() {
        return cpfPassg;
    }

    public void setCpfPassg(Long cpfPassg) {
        this.cpfPassg = cpfPassg;
    }

    public String getCartaoCred() {
        return cartaoCred;
    }

    public void setCartaoCred(String cartaoCred) {
        this.cartaoCred = cartaoCred;
    }

    public String getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(String bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    public String getCidadeOrig() {
        return cidadeOrig;
    }

    public void setCidadeOrig(String cidadeOrig) {
        this.cidadeOrig = cidadeOrig;
    }
}
