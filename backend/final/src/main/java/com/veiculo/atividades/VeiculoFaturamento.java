package com.veiculo.atividades;

import com.tipoPgto.TipoPgto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VeiculoFaturamento {

    String nomeProprietario;
    String placa;
    String descricaoPagamento;
    Double valorTotalFaturado;
    Double valorMedioFaturamento;
}
