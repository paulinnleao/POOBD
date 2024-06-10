package com.viagem;

import com.motoristaVeiculo.MotoristaVeiculo;
import com.passageiro.Passageiro;
import com.tipoPgto.TipoPgto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "VIAGENS")
public class Viagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Embeddable
    public static class ViagemId implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Column(name = "CPF_PASSAG")
        private Long cpfPassag;

        @Column(name = "CPF_MOTORISTA")
        private Long cpfMotorista;

        @Size(max = 7, min = 7, message = "Deve conter 7 caracteres")
        @Column(name = "PLACA", length = 7)
        private String placa;

        @Column(name = "DT_HORA_INICIO")
        private LocalDateTime dtHoraInicio;

        public ViagemId(String placa, Long cpfPassag, Long cpfMotorista, LocalDateTime dthoraInicio) {
            this.cpfMotorista = cpfMotorista;
            this.cpfPassag = cpfPassag;
            this.dtHoraInicio = dthoraInicio;
            this.placa = placa;
        }
        public ViagemId(){};

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

        public LocalDateTime getDtHoraInicio() {
            return dtHoraInicio;
        }

        public void setDtHoraInicio(LocalDateTime dtHoraInicio) {
            this.dtHoraInicio = dtHoraInicio;
        }
    }

    @EmbeddedId
    private ViagemId viagemId = new ViagemId();

    @Column(name = "LOCAL_ORIG_VIAG")
    private String localOrigViag;

    @Column(name = "LOCAL_DEST_VIAG")
    private String localDestViag;

    @Column(name = "DT_HORA_FIM")
    private LocalDateTime dtHoraFim;

    @Column(name = "QTDE_PASS")
    private Integer qtdePass;

    @Column(name = "FORMA_PAGTO")
    private String formaPagto;

    @Column(name = "VALOR_PAGTO")
    private Double valorPagto;

    @Column(name = "CANCELAM_MOT")
    private String cancelamMot;

    @Column(name = "CANCELAM_PASS")
    private String cancelamPass;

    @Column(name = "COD_PAGTO")
    private Integer codPagto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CPF_MOTORISTA", insertable = false, updatable = false),
            @JoinColumn(name = "PLACA", insertable = false, updatable = false)
    })
    private MotoristaVeiculo motoristaVeiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPF_PASSAG", insertable = false, updatable = false)
    private Passageiro passageiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_PAGTO", insertable = false, updatable = false)
    private TipoPgto tipoPgto;

    public Viagem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viagem viagem = (Viagem) o;
        return Objects.equals(viagemId, viagem.viagemId) && Objects.equals(localOrigViag, viagem.localOrigViag) && Objects.equals(localDestViag, viagem.localDestViag) && Objects.equals(dtHoraFim, viagem.dtHoraFim) && Objects.equals(qtdePass, viagem.qtdePass) && Objects.equals(formaPagto, viagem.formaPagto) && Objects.equals(valorPagto, viagem.valorPagto) && Objects.equals(cancelamMot, viagem.cancelamMot) && Objects.equals(cancelamPass, viagem.cancelamPass) && Objects.equals(codPagto, viagem.codPagto) && Objects.equals(motoristaVeiculo, viagem.motoristaVeiculo) && Objects.equals(passageiro, viagem.passageiro) && Objects.equals(tipoPgto, viagem.tipoPgto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(viagemId, localOrigViag, localDestViag, dtHoraFim, qtdePass, formaPagto, valorPagto, cancelamMot, cancelamPass, codPagto, motoristaVeiculo, passageiro, tipoPgto);
    }

    public Integer getCodPagto() {
        return codPagto;
    }

    public void setCodPagto(Integer codPagto) {
        this.codPagto = codPagto;
    }

    public ViagemId getViagemId() {
        return viagemId;
    }

    public void setViagemId(ViagemId viagemId) {
        this.viagemId = viagemId;
    }

    public MotoristaVeiculo getMotoristaVeiculo() {
        return motoristaVeiculo;
    }

    public void setMotoristaVeiculo(MotoristaVeiculo motoristaVeiculo) {
        this.motoristaVeiculo = motoristaVeiculo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public TipoPgto getTipoPgto() {
        return tipoPgto;
    }

    public void setTipoPgto(TipoPgto tipoPgto) {
        this.tipoPgto = tipoPgto;
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

    public LocalDateTime getDtHoraFim() {
        return dtHoraFim;
    }

    public void setDtHoraFim(LocalDateTime dtHoraFim) {
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

    public void setPlaca(String placa){
        this.getViagemId().setPlaca(placa);
    }
    public String getPlaca(){
        return this.getViagemId().getPlaca();
    }
    public void setCpfPassag(Long cpfPassag){
        this.viagemId.setCpfPassag(cpfPassag);
    }
    public Long getCpfPassag(){
       return this.viagemId.getCpfPassag();
    }
    public void setCpfMotorista(Long cpfMotorista){
        this.viagemId.setCpfMotorista(cpfMotorista);
    }
    public Long getCpfMotorista(){
        return this.viagemId.getCpfMotorista();
    }
    public void setDtHoraInicio(LocalDateTime dtHoraInicio){
        this.viagemId.setDtHoraInicio(dtHoraInicio);
    }
    public LocalDateTime getDtHoraInicio(){
        return this.viagemId.getDtHoraInicio();
    }
}
