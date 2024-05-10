package com.viagem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ViagemDTO extends RepresentationModel<ViagemDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id_viagem;

    private Long id_passageiro;

    private Long id_motorista;

    private Long id_veiculo;

    private String local_orig_viag;

    private String local_dest_viag;

    private Date dt_hora_inicio;

    private Date dt_hora_fim;

    private Integer qtde_pass;

    private String forma_pagto;

    private Double valor_pagto;

    private String cancelam_mot;

    private String cancelam_pass;

    public ViagemDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ViagemDTO viagemDTO = (ViagemDTO) o;
        return Objects.equals(id_viagem, viagemDTO.id_viagem) && Objects.equals(id_passageiro, viagemDTO.id_passageiro) && Objects.equals(id_motorista, viagemDTO.id_motorista) && Objects.equals(id_veiculo, viagemDTO.id_veiculo) && Objects.equals(local_orig_viag, viagemDTO.local_orig_viag) && Objects.equals(local_dest_viag, viagemDTO.local_dest_viag) && Objects.equals(dt_hora_inicio, viagemDTO.dt_hora_inicio) && Objects.equals(dt_hora_fim, viagemDTO.dt_hora_fim) && Objects.equals(qtde_pass, viagemDTO.qtde_pass) && Objects.equals(forma_pagto, viagemDTO.forma_pagto) && Objects.equals(valor_pagto, viagemDTO.valor_pagto) && Objects.equals(cancelam_mot, viagemDTO.cancelam_mot) && Objects.equals(cancelam_pass, viagemDTO.cancelam_pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_viagem, id_passageiro, id_motorista, id_veiculo, local_orig_viag, local_dest_viag, dt_hora_inicio, dt_hora_fim, qtde_pass, forma_pagto, valor_pagto, cancelam_mot, cancelam_pass);
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
