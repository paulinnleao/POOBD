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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PASSAGEIRO")
    private Long id_passageiro;

    @Column(name = "CPF_PASSAG")
    private Long cpf_passg;

    @Column(name = "CARTAO_CRED")
    private String cartao_cred;

    @Column(name = "BANDEIRA_CARTAO")
    private String bandeira_cartao;

    @Column(name = "CIDADE_ORIG")
    private String cidade_orig;

    @OneToMany(mappedBy = "passageiro")
    private List<Viagem> listaViagens;


    public Passageiro() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passageiro that = (Passageiro) o;
        return Objects.equals(id_passageiro, that.id_passageiro) && Objects.equals(cpf_passg, that.cpf_passg) && Objects.equals(cartao_cred, that.cartao_cred) && Objects.equals(bandeira_cartao, that.bandeira_cartao) && Objects.equals(cidade_orig, that.cidade_orig) && Objects.equals(listaViagens, that.listaViagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_passageiro, cpf_passg, cartao_cred, bandeira_cartao, cidade_orig, listaViagens);
    }

    public Long getId_passageiro() {
        return id_passageiro;
    }

    public void setId_passageiro(Long id_passageiro) {
        this.id_passageiro = id_passageiro;
    }

    public List<Viagem> getListaViagens() {
        return listaViagens;
    }

    public void setListaViagens(List<Viagem> listaViagens) {
        this.listaViagens = listaViagens;
    }

    public Long getCpf_passg() {
        return cpf_passg;
    }

    public void setCpf_passg(Long cpf_passg) {
        this.cpf_passg = cpf_passg;
    }

    public String getCartao_cred() {
        return cartao_cred;
    }

    public void setCartao_cred(String cartao_cred) {
        this.cartao_cred = cartao_cred;
    }

    public String getBandeira_cartao() {
        return bandeira_cartao;
    }

    public void setBandeira_cartao(String bandeira_cartao) {
        this.bandeira_cartao = bandeira_cartao;
    }

    public String getCidade_orig() {
        return cidade_orig;
    }

    public void setCidade_orig(String cidade_orig) {
        this.cidade_orig = cidade_orig;
    }
}
