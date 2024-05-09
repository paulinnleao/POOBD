package com.tipo_pgto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@Table(name = "TIPOS_PGTOS")
public class TipoPgto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_PAGTO")
    private Integer cod_pagto;
    @Column(name = "DESC_PAGTO")
    private String desc_pagto;
    
}
