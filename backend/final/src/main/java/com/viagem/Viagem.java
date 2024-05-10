package com.viagem;

import com.motorista_veiculo.MotoristaVeiculo;
import com.passageiro.Passageiro;
import com.tipo_pgto.TipoPgto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "VIAGENS")
public class Viagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VIAGEM")
    private Long id_viagem;

    @Column(name = "ID_PASSAGEIRO")
    private Long id_passageiro;

    @Column(name = "ID_MOTORISTA")
    private Long id_motorista;

    @Column(name = "ID_VEICULO")
    private Long id_veiculo;

    @Column(name = "LOCAL_ORIG_VIAG")
    private String local_orig_viag;

    @Column(name = "LOCAL_DEST_VIAG")
    private String local_dest_viag;

    @Column(name = "DT_HORA_INICIO")
    private Date dt_hora_inicio;

    @Column(name = "DT_HORA_FIM")
    private Date dt_hora_fim;

    @Column(name = "QTDE_PASS")
    private Integer qtde_pass;

    @Column(name = "FORMA_PAGTO")
    private String forma_pagto;

    @Column(name = "VALOR_PAGTO")
    private Double valor_pagto;

    @Column(name = "CANCELAM_MOT")
    private String cancelam_mot;

    @Column(name = "CANCELAM_PASS")
    private String cancelam_pass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MOTORISTA")
    private MotoristaVeiculo motoristaVeiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PASSAGEIRO")
    private Passageiro passageiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_PGTO")
    private TipoPgto tipoPgto;

    public Viagem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viagem viagem = (Viagem) o;
        return Objects.equals(id_viagem, viagem.id_viagem) && Objects.equals(id_passageiro, viagem.id_passageiro) && Objects.equals(id_motorista, viagem.id_motorista) && Objects.equals(id_veiculo, viagem.id_veiculo) && Objects.equals(local_orig_viag, viagem.local_orig_viag) && Objects.equals(local_dest_viag, viagem.local_dest_viag) && Objects.equals(dt_hora_inicio, viagem.dt_hora_inicio) && Objects.equals(dt_hora_fim, viagem.dt_hora_fim) && Objects.equals(qtde_pass, viagem.qtde_pass) && Objects.equals(forma_pagto, viagem.forma_pagto) && Objects.equals(valor_pagto, viagem.valor_pagto) && Objects.equals(cancelam_mot, viagem.cancelam_mot) && Objects.equals(cancelam_pass, viagem.cancelam_pass) && Objects.equals(motoristaVeiculo, viagem.motoristaVeiculo) && Objects.equals(passageiro, viagem.passageiro) && Objects.equals(tipoPgto, viagem.tipoPgto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_viagem, id_passageiro, id_motorista, id_veiculo, local_orig_viag, local_dest_viag, dt_hora_inicio, dt_hora_fim, qtde_pass, forma_pagto, valor_pagto, cancelam_mot, cancelam_pass, motoristaVeiculo, passageiro, tipoPgto);
    }

    public Long getId_passageiro() {
        return id_passageiro;
    }

    public void setId_passageiro(Long id_passageiro) {
        this.id_passageiro = id_passageiro;
    }

    public Long getId_motorista() {
        return id_motorista;
    }

    public void setId_motorista(Long id_motorista) {
        this.id_motorista = id_motorista;
    }

    public Long getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Long id_veiculo) {
        this.id_veiculo = id_veiculo;
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

    public Long getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(Long id_viagem) {
        this.id_viagem = id_viagem;
    }

    public String getLocal_orig_viag() {
        return local_orig_viag;
    }

    public void setLocal_orig_viag(String local_orig_viag) {
        this.local_orig_viag = local_orig_viag;
    }

    public String getLocal_dest_viag() {
        return local_dest_viag;
    }

    public void setLocal_dest_viag(String local_dest_viag) {
        this.local_dest_viag = local_dest_viag;
    }

    public Date getDt_hora_inicio() {
        return dt_hora_inicio;
    }

    public void setDt_hora_inicio(Date dt_hora_inicio) {
        this.dt_hora_inicio = dt_hora_inicio;
    }

    public Date getDt_hora_fim() {
        return dt_hora_fim;
    }

    public void setDt_hora_fim(Date dt_hora_fim) {
        this.dt_hora_fim = dt_hora_fim;
    }

    public Integer getQtde_pass() {
        return qtde_pass;
    }

    public void setQtde_pass(Integer qtde_pass) {
        this.qtde_pass = qtde_pass;
    }

    public String getForma_pagto() {
        return forma_pagto;
    }

    public void setForma_pagto(String forma_pagto) {
        this.forma_pagto = forma_pagto;
    }

    public Double getValor_pagto() {
        return valor_pagto;
    }

    public void setValor_pagto(Double valor_pagto) {
        this.valor_pagto = valor_pagto;
    }

    public String getCancelam_mot() {
        return cancelam_mot;
    }

    public void setCancelam_mot(String cancelam_mot) {
        this.cancelam_mot = cancelam_mot;
    }

    public String getCancelam_pass() {
        return cancelam_pass;
    }

    public void setCancelam_pass(String cancelam_pass) {
        this.cancelam_pass = cancelam_pass;
    }
}
