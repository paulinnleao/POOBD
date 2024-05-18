package com.motorista.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class MotoristaDTO extends RepresentationModel<MotoristaDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpfMotorista;
    private String cnh;
    private Integer bancoMot;
    private Integer agenciaMot;
    private Integer contaMot;

    public MotoristaDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MotoristaDTO that = (MotoristaDTO) o;
        return Objects.equals(cpfMotorista, that.cpfMotorista) && Objects.equals(cnh, that.cnh) && Objects.equals(bancoMot, that.bancoMot) && Objects.equals(agenciaMot, that.agenciaMot) && Objects.equals(contaMot, that.contaMot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpfMotorista, cnh, bancoMot, agenciaMot, contaMot);
    }

    public Long getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(Long cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Integer getBancoMot() {
        return bancoMot;
    }

    public void setBancoMot(Integer bancoMot) {
        this.bancoMot = bancoMot;
    }

    public Integer getAgenciaMot() {
        return agenciaMot;
    }

    public void setAgenciaMot(Integer agenciaMot) {
        this.agenciaMot = agenciaMot;
    }

    public Integer getContaMot() {
        return contaMot;
    }

    public void setContaMot(Integer contaMot) {
        this.contaMot = contaMot;
    }
}
