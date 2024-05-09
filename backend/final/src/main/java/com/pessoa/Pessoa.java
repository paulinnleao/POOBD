package com.pessoa;

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
@Table(name = "PESSOAS")
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_PESSOA")
    private Long cpf_pessoa;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "ENDERECO")
    private String endereco;
    @Column(name = "TELEFONE")
    private Integer telefone;
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "E_MAIL")
    private String e_mail;
}
