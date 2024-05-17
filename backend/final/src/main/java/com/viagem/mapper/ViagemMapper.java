package com.viagem.mapper;

import com.util.mapper.GlobalMapper;
import com.viagem.Viagem;
import com.viagem.dto.ViagemDTO;

public class ViagemMapper extends GlobalMapper {
    static {
        mapper.typeMap(Viagem.class, ViagemDTO.class).addMappings(map -> {
            map.map(Viagem::getCpf_pass, ViagemDTO::setCpf_passag);
            map.map(Viagem::getCpf_motorista, ViagemDTO::setCpf_motorista);
            map.map(Viagem::getDt_hora_inicio, ViagemDTO::setDt_hora_inicio);
            map.map(Viagem::getPlaca, ViagemDTO::setPLACA);
        });
        mapper.typeMap(ViagemDTO.class, Viagem.class).addMappings(map -> {
            map.map(ViagemDTO::getCpf_passag, Viagem::setCpf_passg);
            map.map(ViagemDTO::getCpf_motorista, Viagem::setCpf_motorista);
            map.map(ViagemDTO::getPLACA, Viagem::setPlaca);
            map.map(ViagemDTO::getDt_hora_inicio, Viagem::setDt_hora_inicio);
        });
    }
}
