package com.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class PessoaDTO extends RepresentationModel<PessoaDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpf_pessoa;
    private String nome;
    private String endereco;
    private Integer telefone;
    private String sexo;
    private String e_mail;

    public PessoaDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PessoaDTO pessoaDTO = (PessoaDTO) o;
        return Objects.equals(cpf_pessoa, pessoaDTO.cpf_pessoa) && Objects.equals(nome, pessoaDTO.nome) && Objects.equals(endereco, pessoaDTO.endereco) && Objects.equals(telefone, pessoaDTO.telefone) && Objects.equals(sexo, pessoaDTO.sexo) && Objects.equals(e_mail, pessoaDTO.e_mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpf_pessoa, nome, endereco, telefone, sexo, e_mail);
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
}
