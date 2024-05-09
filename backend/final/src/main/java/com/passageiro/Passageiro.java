package com.passageiro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@Table(name = "PASSAGEIROS")
public class Passageiro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CPF_PASSAG")
    private Long cpf_passg;
    @Column(name = "CARTAO_CRED")
    private String cartao_cred;
    @Column(name = "BANDEIRA_CARTAO")
    private String bandeira_cartao;
    @Column(name = "CIDADE_ORIG")
    private String cidade_orig;
}
