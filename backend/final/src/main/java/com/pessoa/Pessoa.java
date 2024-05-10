package com.pessoa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@Table(name = "PESSOAS")
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA")
    private Long id_pessoa;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id_pessoa, pessoa.id_pessoa) && Objects.equals(cpf_pessoa, pessoa.cpf_pessoa) && Objects.equals(nome, pessoa.nome) && Objects.equals(endereco, pessoa.endereco) && Objects.equals(telefone, pessoa.telefone) && Objects.equals(sexo, pessoa.sexo) && Objects.equals(e_mail, pessoa.e_mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pessoa, cpf_pessoa, nome, endereco, telefone, sexo, e_mail);
    }

    public Long getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(Long id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public Long getCpf_pessoa() {
        return cpf_pessoa;
    }

    public void setCpf_pessoa(Long cpf_pessoa) {
        this.cpf_pessoa = cpf_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public Pessoa() {
    }
}
