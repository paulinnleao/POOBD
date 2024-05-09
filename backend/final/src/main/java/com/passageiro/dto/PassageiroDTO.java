package com.passageiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class PassageiroDTO extends RepresentationModel<PassageiroDTO> implements Serializable {
    @Serial
    public static final long serialVersionUID = 1L;

    private Long cpf_passg;
    private String cartao_cred;
    private String bandeira_cartao;
    private String cidade_orig;

    public PassageiroDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PassageiroDTO that = (PassageiroDTO) o;
        return Objects.equals(cpf_passg, that.cpf_passg) && Objects.equals(cartao_cred, that.cartao_cred) && Objects.equals(bandeira_cartao, that.bandeira_cartao) && Objects.equals(cidade_orig, that.cidade_orig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpf_passg, cartao_cred, bandeira_cartao, cidade_orig);
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
