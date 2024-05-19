package com.viagem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ViagemDTO extends RepresentationModel<ViagemDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpfPassag;

    private Long cpfMotorista;

    private String placa;

    private String localOrigViag;

    private String localDestViag;

    private LocalDateTime dtHoraInicio;

    private Date dtHoraFim;

    private Integer qtdePass;

    private String formaPagto;

    private Double valorPagto;

    private String cancelamMot;

    private String cancelamPass;

    private Integer codPagto;

    public ViagemDTO() {
    }

    public Long getCpfPassag() {
        return cpfPassag;
    }

    public void setCpfPassag(Long cpfPassag) {
        this.cpfPassag = cpfPassag;
    }

    public Long getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(Long cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ViagemDTO viagemDTO = (ViagemDTO) o;
        return Objects.equals(cpfPassag, viagemDTO.cpfPassag) && Objects.equals(cpfMotorista, viagemDTO.cpfMotorista) && Objects.equals(placa, viagemDTO.placa) && Objects.equals(localOrigViag, viagemDTO.localOrigViag) && Objects.equals(localDestViag, viagemDTO.localDestViag) && Objects.equals(dtHoraInicio, viagemDTO.dtHoraInicio) && Objects.equals(dtHoraFim, viagemDTO.dtHoraFim) && Objects.equals(qtdePass, viagemDTO.qtdePass) && Objects.equals(formaPagto, viagemDTO.formaPagto) && Objects.equals(valorPagto, viagemDTO.valorPagto) && Objects.equals(cancelamMot, viagemDTO.cancelamMot) && Objects.equals(cancelamPass, viagemDTO.cancelamPass) && Objects.equals(codPagto, viagemDTO.codPagto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpfPassag, cpfMotorista, placa, localOrigViag, localDestViag, dtHoraInicio, dtHoraFim, qtdePass, formaPagto, valorPagto, cancelamMot, cancelamPass, codPagto);
    }

    public Integer getCodPagto() {
        return codPagto;
    }

    public void setCodPagto(Integer codPagto) {
        this.codPagto = codPagto;
    }

    public String getLocalOrigViag() {
        return localOrigViag;
    }

    public void setLocalOrigViag(String localOrigViag) {
        this.localOrigViag = localOrigViag;
    }

    public String getLocalDestViag() {
        return localDestViag;
    }

    public void setLocalDestViag(String localDestViag) {
        this.localDestViag = localDestViag;
    }

    public LocalDateTime getDtHoraInicio() {
        return dtHoraInicio;
    }

    public void setDtHoraInicio(LocalDateTime dtHoraInicio) {
        this.dtHoraInicio = dtHoraInicio;
    }

    public Date getDtHoraFim() {
        return dtHoraFim;
    }

    public void setDtHoraFim(Date dtHoraFim) {
        this.dtHoraFim = dtHoraFim;
    }

    public Integer getQtdePass() {
        return qtdePass;
    }

    public void setQtdePass(Integer qtdePass) {
        this.qtdePass = qtdePass;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public Double getValorPagto() {
        return valorPagto;
    }

    public void setValorPagto(Double valorPagto) {
        this.valorPagto = valorPagto;
    }

    public String getCancelamMot() {
        return cancelamMot;
    }

    public void setCancelamMot(String cancelamMot) {
        this.cancelamMot = cancelamMot;
    }

    public String getCancelamPass() {
        return cancelamPass;
    }

    public void setCancelamPass(String cancelamPass) {
        this.cancelamPass = cancelamPass;
    }
}
