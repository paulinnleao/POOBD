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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOTORISTA_VEICULO")
    private Long id_motorista_veiculo;

    @Column(name = "CPF_MOTORISTA")
    private Long cpf_motorista;

    @Column(name = "ID_VEICULO")
    private Long id_veiculo;

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

    public Long getId_motorista_veiculo() {
        return id_motorista_veiculo;
    }

    public void setId_motorista_veiculo(Long id_motorista_veiculo) {
        this.id_motorista_veiculo = id_motorista_veiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotoristaVeiculo that = (MotoristaVeiculo) o;
        return Objects.equals(id_motorista_veiculo, that.id_motorista_veiculo) && Objects.equals(cpf_motorista, that.cpf_motorista) && Objects.equals(id_veiculo, that.id_veiculo) && Objects.equals(motorista, that.motorista) && Objects.equals(veiculo, that.veiculo) && Objects.equals(listaViagens, that.listaViagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_motorista_veiculo, cpf_motorista, id_veiculo, motorista, veiculo, listaViagens);
    }

    public Long getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Long id_veiculo) {
        this.id_veiculo = id_veiculo;
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

}
