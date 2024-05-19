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

    private Long cpfPessoa;
    private String nome;
    private String endereco;
    private Long telefone;
    private String sexo;
    private String eMail;

    public PessoaDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PessoaDTO pessoaDTO = (PessoaDTO) o;
        return Objects.equals(cpfPessoa, pessoaDTO.cpfPessoa) && Objects.equals(nome, pessoaDTO.nome) && Objects.equals(endereco, pessoaDTO.endereco) && Objects.equals(telefone, pessoaDTO.telefone) && Objects.equals(sexo, pessoaDTO.sexo) && Objects.equals(eMail, pessoaDTO.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpfPessoa, nome, endereco, telefone, sexo, eMail);
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

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
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
}
