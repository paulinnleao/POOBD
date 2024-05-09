package com.proprietario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
@Table(name = "PROPRIETARIOS")
public class Proprietario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_PROP")
    private Long cpf_prop;
    @Column(name = "CNH_PROP")
    private String cnh_prop;
    @Column(name = "BANCO_PROP")
    private Integer banco_prop;
    @Column(name = "AGENCIA_PROP")
    private Integer agencia_prop;
    @Column(name = "CONTA_PROP")
    private Integer conta_prop;
    
}
