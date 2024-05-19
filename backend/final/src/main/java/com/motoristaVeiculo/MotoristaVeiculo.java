package com.motoristaVeiculo;

import com.motorista.Motorista;
import com.veiculo.Veiculo;
import com.viagem.Viagem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@Table(name = "MOTORISTAS_VEICULOS")
public class MotoristaVeiculo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Embeddable
    public static class MotoristaVeiculoId implements Serializable {
        @Serial
        public static final long serialVersionUID = 1L;

        @NotNull(message = "CPF não pode ser nulo")
        @Column(name = "CPF_MOTORISTA")
        private Long cpfMotorista;

        @Column(name = "PLACA")
        private String placa;

        public MotoristaVeiculoId(){}
        public MotoristaVeiculoId(Long cpfMotorista, String placa) {
            this.cpfMotorista = cpfMotorista;
            this.placa = placa;
        }

        public Long getCpfMotorista() {
            return cpfMotorista;
        }

        public void setCpfMotorista(Long cpfMotorista) {
            this.cpfMotorista = cpfMotorista;
        }

        public String getPlaca() {
            return this.placa;
        }

        public void setPlaca(String placa) {
            this.placa = placa;
        }
    }

    @EmbeddedId
    private MotoristaVeiculoId motoristaVeiculoId = new MotoristaVeiculoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPF_MOTORISTA", insertable = false, updatable = false)
    private Motorista motorista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACA", insertable = false, updatable = false)
    private Veiculo veiculo;

    @OneToMany(mappedBy = "motoristaVeiculo")
    private List<Viagem> listaViagens = new ArrayList<Viagem>();

    public MotoristaVeiculo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotoristaVeiculo that = (MotoristaVeiculo) o;
        return Objects.equals(motoristaVeiculoId, that.motoristaVeiculoId) && Objects.equals(motorista, that.motorista) && Objects.equals(veiculo, that.veiculo) && Objects.equals(listaViagens, that.listaViagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motoristaVeiculoId, motorista, veiculo, listaViagens);
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

    public MotoristaVeiculoId getMotoristaVeiculoId() {
        return motoristaVeiculoId;
    }

    public void setMotoristaVeiculoId(MotoristaVeiculoId motoristaVeiculoId) {
        this.motoristaVeiculoId = motoristaVeiculoId;
    }

    public List<Viagem> getListaViagens() {
        return listaViagens;
    }

    public void setListaViagens(List<Viagem> listaViagens) {
        this.listaViagens = listaViagens;
    }

    public void setCpfMotorista(Long cpfMotorista){
        this.motoristaVeiculoId.setCpfMotorista(cpfMotorista);
    }
    public Long getCpfMotorista(){
        return this.motoristaVeiculoId.getCpfMotorista();
    }
    public void setPlaca(String placa){
        this.motoristaVeiculoId.setPlaca(placa);
    }
    public String getPlaca(){
        return this.motoristaVeiculoId.getPlaca();
    }

}
