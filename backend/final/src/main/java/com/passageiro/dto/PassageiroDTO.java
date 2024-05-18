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

    private Long cpfPassg;
    private String cartaoCred;
    private String bandeiraCartao;
    private String cidadeOrig;

    public PassageiroDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PassageiroDTO that = (PassageiroDTO) o;
        return Objects.equals(cpfPassg, that.cpfPassg) && Objects.equals(cartaoCred, that.cartaoCred) && Objects.equals(bandeiraCartao, that.bandeiraCartao) && Objects.equals(cidadeOrig, that.cidadeOrig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpfPassg, cartaoCred, bandeiraCartao, cidadeOrig);
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
