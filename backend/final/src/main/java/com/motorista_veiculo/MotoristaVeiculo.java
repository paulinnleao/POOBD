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

    private class MotoristaVeiculoPk implements Serializable {
        @Column(name = "CPF_MOTORISTA")
        private Long cpf_motorista;

        @Column(name = "ID_VEICULO")
        private Long id_veiculo;

        public Long getCpf_motorista() {
            return cpf_motorista;
        }

        public void setCpf_motorista(Long cpf_motorista) {
            this.cpf_motorista = cpf_motorista;
        }

        public Long getId_veiculo() {
            return id_veiculo;
        }

        public void setId_veiculo(Long id_veiculo) {
            this.id_veiculo = id_veiculo;
        }
    }

    @Id
    private MotoristaVeiculoPk motorista_veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MOTORISTA")
    private Motorista motorista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACA")
    private Veiculo veiculo;

    @OneToMany(mappedBy = "motoristaVeiculo")
    private List<Viagem> listaViagens;

    public MotoristaVeiculo() {
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

}
