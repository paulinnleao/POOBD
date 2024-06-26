package com.viagem.service;

import com.tipoPgto.rest.TipoPgtoRestImp;
import com.util.atividades.ViagensMediaMensalSexo;
import com.util.exception.ResourceAlreadyExistsException;
import com.util.exception.ResourceNotFoundException;
import com.viagem.Viagem;
import com.viagem.dto.ViagemDTO;
import com.viagem.mapper.ViagemMapper;
import com.viagem.repository.ViagemRepository;
import com.viagem.rest.ViagemRestImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
 public class ViagemServiceImp implements ViagemService{
    @Autowired
    ViagemRepository repository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public ViagemDTO findById( Long cpfPassag, Long cpfMotorista,String placa, String dtHora) {
        LocalDateTime dthoraInicio = LocalDateTime.parse(dtHora, formatter);
        Viagem viagem = repository.findById(new Viagem.ViagemId(placa, cpfPassag, cpfMotorista, dthoraInicio))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado viagem para este ID!"));
        ViagemDTO viagemDTO = ViagemMapper.parseObject(viagem, ViagemDTO.class);
        viagemDTO.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(cpfPassag, cpfMotorista,placa, dthoraInicio.toString())
                ).withSelfRel()
        );
        viagemDTO.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(
                                viagem.getCpfPassag(),
                                viagem.getCpfMotorista(),
                                viagem.getPlaca(),
                                viagem.getDtHoraInicio().toString()
                        )
                ).withRel("End point com ID do tipo de pagamento")
        );
        return viagemDTO;
    }

    @Override
    public List<ViagemDTO> findAll() {
        List<Viagem> listaViagens = repository.findAll();
        List<ViagemDTO> listaViagensDTO = ViagemMapper.parseListObject(listaViagens, ViagemDTO.class);
        listaViagensDTO.forEach(
                viagem ->
                listaViagens.forEach(
                        viagemRepository ->
                        {
                            if((
                                viagemRepository.getPlaca()
                                +viagemRepository.getCpfPassag()
                                +viagemRepository.getCpfMotorista()
                                +viagemRepository.getDtHoraInicio()).equals(
                                    viagem.getPlaca()+
                                    viagem.getCpfPassag().toString()+
                                    viagem.getCpfMotorista().toString()+
                                    viagem.getDtHoraInicio().toString()
                                    ))
                            {
                                viagem.add(
                                        linkTo(
                                                methodOn(ViagemRestImp.class).findById(
                                                        viagem.getCpfPassag(),
                                                        viagem.getCpfMotorista(),
                                                        viagem.getPlaca(),
                                                        viagem.getDtHoraInicio().toString()
                                                )
                                        ).withSelfRel()
                                );
                                viagem.add(
                                        linkTo(
                                                methodOn(TipoPgtoRestImp.class).findById(
                                                        viagemRepository.getTipoPgto().getCodPagto()
                                                )
                                        ).withRel("End point com ID do tipo de pagamento")
                                );
                            }
                        }
                )
        );
        return listaViagensDTO;
    }

    @Override
    public ViagemDTO create(ViagemDTO viagemDTO) {
        if( repository.findById(
                new Viagem.ViagemId(
                        viagemDTO.getPlaca(),
                        viagemDTO.getCpfPassag(),
                        viagemDTO.getCpfMotorista(),
                        viagemDTO.getDtHoraInicio()
                )).isPresent()){
            throw new ResourceAlreadyExistsException("Já existe uma viagem cadastrada com este ID!");
        }
        Viagem viagem = ViagemMapper.parseObject(viagemDTO, Viagem.class);
        ViagemDTO viagemDTOSaved = ViagemMapper.parseObject(
                repository.save(viagem),
                ViagemDTO.class
        );
        viagemDTOSaved.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(
                                viagemDTOSaved.getCpfPassag(),
                                viagemDTOSaved.getCpfMotorista(),
                                viagemDTOSaved.getPlaca(),
                                viagemDTOSaved.getDtHoraInicio().toString()
                        )
                ).withSelfRel()
        );
        viagemDTOSaved.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(
                                viagemDTOSaved.getCpfPassag(),
                                viagemDTOSaved.getCpfMotorista(),
                                viagemDTOSaved.getPlaca(),
                                viagemDTOSaved.getDtHoraInicio().toString()
                        )
                ).withRel("End point com ID do tipo de pagamento")
        );
        return viagemDTOSaved;
    }

    @Override
    public ViagemDTO update(ViagemDTO viagemDTO) {
        if( repository.findById(
                new Viagem.ViagemId(
                        viagemDTO.getPlaca(),
                        viagemDTO.getCpfPassag(),
                        viagemDTO.getCpfMotorista(),
                        viagemDTO.getDtHoraInicio()
                )).isEmpty()){
            throw  new ResourceNotFoundException("Não foi encontrado esta viagem para atualizar!");
        }
        Viagem viagem = ViagemMapper.parseObject(viagemDTO, Viagem.class);
        ViagemDTO viagemDTOSaved = ViagemMapper.parseObject(
                repository.save(viagem),
                ViagemDTO.class
        );
        viagemDTOSaved.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(
                                viagemDTOSaved.getCpfPassag(),
                                viagemDTOSaved.getCpfMotorista(),
                                viagemDTOSaved.getPlaca(),
                                viagemDTOSaved.getDtHoraInicio().toString()
                        )
                ).withRel("End point com ID do tipo de pagamento")
        );
        viagemDTOSaved.add(
                linkTo(
                        methodOn(TipoPgtoRestImp.class).findById(
                                viagem.getTipoPgto().getCodPagto()
                        )
                ).withRel("")
        );
        return viagemDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(String placa, Long cpfPassag, Long cpfMotorista, String dtHora) {
        LocalDateTime dthoraInicio = LocalDateTime.parse(dtHora, formatter);
        Viagem viagem = repository.findById(new Viagem.ViagemId(placa, cpfPassag, cpfMotorista, dthoraInicio))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado viagem para este ID!"));
        repository.delete(viagem);
        return ResponseEntity.noContent().build();
    }

    // Fase 02 - atividade 01
    @Override
    public List<ViagemDTO> findByDate(LocalDateTime dtHoraInicio, LocalDateTime dtHoraFim){
        List<Viagem> viagensBuscada = repository.findByDate(dtHoraInicio, dtHoraFim);
        if(viagensBuscada.isEmpty()){
         throw new ResourceNotFoundException("Não existem viagens para a data, hora inicio e hora fim fornecida!");
        }
        return ViagemMapper.parseListObject(viagensBuscada, ViagemDTO.class);
    };

    // Fase 02 - atividade 02
    @Override
    public List<ViagemDTO> faturamentoPorMes(Integer quantidade, Integer mes){
        List<Viagem> viagensBuscada = repository.faturamentoPorMesEQuantidade(quantidade, mes);
        if(viagensBuscada.isEmpty()){
            throw new ResourceNotFoundException("Não existem viagens para este mês!");
        }
        return ViagemMapper.parseListObject(viagensBuscada, ViagemDTO.class);
    }

    // Fase 02 - atividade 04
    @Override
    public ResponseEntity<List<ViagensMediaMensalSexo>> buscarViagensMediaMensalSexo(){
        List<ViagensMediaMensalSexo> viagensMediaMensalSexoLista = new ArrayList<>();
        var resultado = repository.buscarViagensMediaMensalSexo();
        resultado.forEach(objeto -> {
            int mesNumero = ((BigDecimal) objeto[0]).intValue();
            String mes = Month.of(mesNumero).getDisplayName(TextStyle.FULL, Locale.getDefault());
            Double media = ((BigDecimal) objeto[2]).setScale(2, RoundingMode.HALF_UP).doubleValue();

            // Verifica se já existe um elemento com o mesmo mês na lista
            boolean existeMes = false;
            for (ViagensMediaMensalSexo registro : viagensMediaMensalSexoLista) {
                if (registro.getMes().equals(mes)) {
                    registro.setMediaFeminino(media);
                    existeMes = true;
                    break;
                }
            }

            // Se não existir, adiciona um novo elemento com valores padrão
            if (!existeMes) {
                viagensMediaMensalSexoLista.add(new ViagensMediaMensalSexo(mes, media));
            }
        });

        return ResponseEntity.ok(viagensMediaMensalSexoLista);
    }
}
