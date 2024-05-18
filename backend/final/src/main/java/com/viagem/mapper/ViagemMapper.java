package com.viagem.mapper;

import com.util.mapper.GlobalMapper;
import com.viagem.Viagem;
import com.viagem.dto.ViagemDTO;

public class ViagemMapper extends GlobalMapper {
    static {
        mapper.typeMap(Viagem.class, ViagemDTO.class).addMappings(map -> {
            map.map(Viagem::getCpfPassag, ViagemDTO::setCpfPassag);
            map.map(Viagem::getCpfMotorista, ViagemDTO::setCpfMotorista);
            map.map(Viagem::getDtHoraInicio, ViagemDTO::setDtHoraInicio);
            map.map(Viagem::getPlaca, ViagemDTO::setPlaca);
        });
        mapper.typeMap(ViagemDTO.class, Viagem.class).addMappings(map -> {
            map.map(ViagemDTO::getCpfPassag, Viagem::setCpfPassag);
            map.map(ViagemDTO::getCpfMotorista, Viagem::setCpfMotorista);
            map.map(ViagemDTO::getPlaca, Viagem::setPlaca);
            map.map(ViagemDTO::getDtHoraInicio, Viagem::setDtHoraInicio);
        });
    }
}
