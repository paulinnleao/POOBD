package com.motorista_veiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class MotoristaVeiculoDTO extends RepresentationModel<MotoristaVeiculoDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long cpfMotorista;
    private String placa;

    public MotoristaVeiculoDTO() {
    }


    public Long getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(Long cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
