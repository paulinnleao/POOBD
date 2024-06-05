package com.veiculo.service;

import com.veiculo.atividades.VeiculoFaturamento;
import com.veiculo.dto.VeiculoDTO;
import com.viagem.Viagem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VeiculoService {

    VeiculoDTO findById(String placa);
    List<VeiculoDTO> findAll();
    VeiculoDTO create(VeiculoDTO veiculoDTO);
    VeiculoDTO update(VeiculoDTO veiculoAtualizado);
    ResponseEntity<?> delete(String placa);

    //Fase 02 - atividade 01
    List<VeiculoDTO> findByDate(String data, String horaInicio, String horaFinal);

    //Fase 02 - atividade 03
    ResponseEntity<List<VeiculoFaturamento>> faturamentoVeiculos(Integer mes);
    Double valorTotalFaturado(String placa, List<Viagem> listaViagens);
    Double valorMedioFaturado(String placa, List<Viagem> listaViagens);
}
