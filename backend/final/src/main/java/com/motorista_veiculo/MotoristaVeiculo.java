package com.motorista_veiculo;

import com.motorista.Motorista;
import com.veiculo.Veiculo;
import com.viagem.Viagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@Table(name = "MOTORISTAS_VEICULOS")
public class MotoristaVeiculo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPF_MOTORISTA")
    private Long cpf_motorista;
    @Column(name = "PLACA_VEICULO")
    private String placa_veiculo;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CPF_MOTORISTA")
    private Motorista motorista;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACA")
    private Veiculo veiculo;
    @OneToMany(mappedBy = "motoristaVeiculo")
    private List<Viagem> listaViagens;
    public MotoristaVeiculo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotoristaVeiculo that = (MotoristaVeiculo) o;
        return Objects.equals(cpf_motorista, that.cpf_motorista) && Objects.equals(placa_veiculo, that.placa_veiculo) && Objects.equals(motorista, that.motorista) && Objects.equals(veiculo, that.veiculo) && Objects.equals(listaViagens, that.listaViagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf_motorista, placa_veiculo, motorista, veiculo, listaViagens);
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<Viagem> getListaViagens() {
        return listaViagens;
    }

    public void setListaViagens(List<Viagem> listaViagens) {
        this.listaViagens = listaViagens;
    }

    public Long getCpf_motorista() {
        return cpf_motorista;
    }

    public void setCpf_motorista(Long cpf_motorista) {
        this.cpf_motorista = cpf_motorista;
    }

    public String getPlaca_veiculo() {
        return placa_veiculo;
    }

    public void setPlaca_veiculo(String placa_veiculo) {
        this.placa_veiculo = placa_veiculo;
    }
}
