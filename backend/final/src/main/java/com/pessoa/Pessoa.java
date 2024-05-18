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
    @Column(name = "CPF_PESSOA")
    private Long cpfPessoa;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "TELEFONE")
    private Integer telefone;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "E_MAIL")
    private String eMail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(cpfPessoa, pessoa.cpfPessoa) && Objects.equals(nome, pessoa.nome) && Objects.equals(endereco, pessoa.endereco) && Objects.equals(telefone, pessoa.telefone) && Objects.equals(sexo, pessoa.sexo) && Objects.equals(eMail, pessoa.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfPessoa, nome, endereco, telefone, sexo, eMail);
    }

    public Long getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(Long cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Pessoa() {
    }
}
