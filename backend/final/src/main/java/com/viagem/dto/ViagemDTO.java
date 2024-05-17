package com.viagem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class ViagemDTO extends RepresentationModel<ViagemDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpf_passag;

    private Long cpf_motorista;

    private Long PLACA;

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
