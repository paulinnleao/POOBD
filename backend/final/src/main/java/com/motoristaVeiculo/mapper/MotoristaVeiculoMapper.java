package com.motoristaVeiculo.mapper;

import com.motoristaVeiculo.MotoristaVeiculo;
import com.motoristaVeiculo.dto.MotoristaVeiculoDTO;
import com.util.mapper.GlobalMapper;

public class MotoristaVeiculoMapper extends GlobalMapper {
    static {
        mapper.typeMap(MotoristaVeiculo.class, MotoristaVeiculoDTO.class).addMappings(map -> {
            map.map(MotoristaVeiculo::getPlaca, MotoristaVeiculoDTO::setCpfMotorista);
            map.map(MotoristaVeiculo::getCpfMotorista, MotoristaVeiculoDTO::setCpfMotorista);
        });
        mapper.typeMap(MotoristaVeiculoDTO.class, MotoristaVeiculo.class).addMappings(map -> {
            map.map(MotoristaVeiculoDTO::getPlaca, MotoristaVeiculo::setPlaca);
            map.map(MotoristaVeiculoDTO::getCpfMotorista, MotoristaVeiculo::setCpfMotorista);
        });
    }
}
