package com.veiculo.atividades;

import com.tipoPgto.TipoPgto;
import lombok.Data;

@Data
public class VeiculoFaturamento {

    String nomeProprietario;
    String placa;
    TipoPgto tipoPgto;
    Double valorTotalFaturado;
    Double valorMedioFaturamento;


}
