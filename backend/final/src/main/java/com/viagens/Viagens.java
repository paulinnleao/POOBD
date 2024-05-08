package com.viagens;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@Entity
@Table(name = "VIAGEM")
public class Viagens implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VIAGEM")
    private Long id_viagem;
    @Column(name = "CPF_PASS_VIAG")
    private Long cpf_pass_viag;
    @Column(name = "CPF_MOT_VIAG")
    private Long cpf_mot_viag;
    @Column(name = "PLACA_VEIC_VIAG")
    private String placa_veic_viag;
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
    
}
