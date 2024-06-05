package com.veiculo.service;

import com.pessoa.service.PessoaServiceImp;
import com.util.exception.ResourceAlreadyExistsException;
import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import com.veiculo.Veiculo;
import com.veiculo.atividades.VeiculoFaturamento;
import com.veiculo.dto.VeiculoDTO;
import com.veiculo.repository.VeiculoRepository;
import com.veiculo.rest.VeiculoRestImp;
import com.viagem.Viagem;
import com.viagem.dto.ViagemDTO;
import com.viagem.repository.ViagemRepository;
import com.viagem.service.ViagemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoServiceImp implements VeiculoService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    private VeiculoRepository repository;

    //Fase 02 - Atividade 01
    @Autowired
    private ViagemServiceImp viagemService;
    //Fase 02 - Atividade 01
    @Autowired
    private ViagemRepository viagemRepository;
    //Fase 02 - Atividade 03
    @Autowired
    private PessoaServiceImp pessoaServiceImp;

    @Override
    public VeiculoDTO findById(String placa) {
        Veiculo veiculo = repository.findById(placa)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado veículo para esta placa!"));
        VeiculoDTO veiculoDTO = GlobalMapper.parseObject(veiculo, VeiculoDTO.class);
        veiculoDTO.add(
                linkTo(
                        methodOn(VeiculoRestImp.class).findById(placa)
                ).withSelfRel()
        );
        return veiculoDTO;
    }

    @Override
    public List<VeiculoDTO> findAll() {
        List<Veiculo> listaVeiculos = repository.findAll();
        List<VeiculoDTO> listaVeiculoDTO = GlobalMapper.parseListObject(listaVeiculos, VeiculoDTO.class);
        listaVeiculoDTO.forEach(
                veiculo -> veiculo.add(
                        linkTo(
                                methodOn(VeiculoRestImp.class).findById(veiculo.getPlaca())
                        ).withSelfRel()
                )
        );
        return listaVeiculoDTO;
    }

    @Override
    public VeiculoDTO create(VeiculoDTO veiculoDTO) {
        if(repository.findById(veiculoDTO.getPlaca()).isPresent()){
            throw new ResourceAlreadyExistsException("Já existe veículo cadastrado para esta placa!");
        }
        Veiculo veiculo = GlobalMapper.parseObject(veiculoDTO, Veiculo.class);
        VeiculoDTO veiculoDTOSaved = GlobalMapper.parseObject(
                repository.save(veiculo),
                VeiculoDTO.class
        );
        veiculoDTOSaved.add(
                linkTo(
                        methodOn(VeiculoRestImp.class).findById(veiculoDTOSaved.getPlaca())
                ).withSelfRel()
        );
        return veiculoDTOSaved;
    }

    @Override
    public VeiculoDTO update(VeiculoDTO veiculoAtualizado) {
        if(repository.findById(veiculoAtualizado.getPlaca()).isEmpty()){
            throw  new ResourceNotFoundException("Não foi encontrado este veículo para atualizar!");
        }
        Veiculo veiculo = GlobalMapper.parseObject(veiculoAtualizado, Veiculo.class);
        VeiculoDTO veiculoDTOSaved = GlobalMapper.parseObject(
                repository.save(veiculo),
                VeiculoDTO.class
        );
        veiculoDTOSaved.add(
                linkTo(
                        methodOn(VeiculoRestImp.class).findById(veiculoDTOSaved.getPlaca())
                ).withSelfRel()
        );
        return veiculoDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(String placa) {
        Veiculo veiculo = repository.findById(placa)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado veículo para esta placa!"));
        repository.delete(veiculo);
        return ResponseEntity.noContent().build();
    }

    //Fase 02 - atividade 01
    @Override
    public List<VeiculoDTO> findByDate(String data, String horaInicio, String horaFinal){
        LocalDateTime dtHoraInicio = LocalDateTime.parse(data + "T" + horaInicio, formatter);
        LocalDateTime dtHoraFim = LocalDateTime.parse(data + "T" + horaFinal, formatter);
        List<ViagemDTO> viagensBuscadas = viagemService.findByDate(dtHoraInicio, dtHoraFim);
        List<VeiculoDTO> veiculosBuscadosDTO = new ArrayList<>();
        viagensBuscadas.forEach(viagem -> repository
            .findById(viagem.getPlaca())
            .ifPresent(
                    veiculo -> veiculosBuscadosDTO.add(
                            GlobalMapper.parseObject(veiculo, VeiculoDTO.class)
        )));
        veiculosBuscadosDTO.forEach(
                veiculo -> veiculo.add(
                        linkTo(
                                methodOn(VeiculoRestImp.class).findById(veiculo.getPlaca())
                        ).withSelfRel()
                )
        );
        return veiculosBuscadosDTO;
    }

    //Fase 02 - atividade 03
    @Override
    public ResponseEntity<List<VeiculoFaturamento>> faturamentoVeiculos(Integer mes){
        List<VeiculoFaturamento> veiculosFaturamento = new ArrayList<>();
        List<Viagem> listaViagens = viagemRepository.faturamentoPorMes(mes);
        List<Veiculo> listaVeiculos = new ArrayList<>();
        listaViagens.forEach(
                viagem -> repository.findById(
                        viagem.getPlaca())
                        .ifPresent(listaVeiculos::add));
        for(int i = 0; i<listaViagens.size(); i++){
            veiculosFaturamento.add(
                    new VeiculoFaturamento(
                            pessoaServiceImp.findById(
                                    listaVeiculos
                                            .get(i)
                                            .getProprietario()
                                            .getCpfProp()
                            )
                                    .getNome(),
                            listaVeiculos.get(i).getPlaca(),
                            listaViagens.get(i).getTipoPgto().getDescPagto(),
                            valorTotalFaturado(listaViagens.get(i).getPlaca(), listaViagens),
                            valorMedioFaturado(listaViagens.get(i).getPlaca(), listaViagens)
                    ));
        }
        return ResponseEntity.ok(veiculosFaturamento);
    }

    @Override
    public Double valorTotalFaturado(String placa, List<Viagem> listaViagens){
        return listaViagens.stream()
                .filter(viagem -> viagem.getPlaca().equals(placa))
                .mapToDouble(Viagem::getValorPagto)
                .sum();
    }

    @Override
    public Double valorMedioFaturado(String placa, List<Viagem> listaViagens) {
        double[] mediaEQuantidade = listaViagens.stream()
                .filter(viagem -> viagem.getPlaca().equals(placa))
                .mapToDouble(Viagem::getValorPagto)
                .collect(() -> new double[2],
                        (total, valor) -> { total[0] += valor; total[1]++; },
                        (a, b) -> { a[0] += b[0]; a[1] += b[1]; });

        double total = mediaEQuantidade[0];
        double quantidade = mediaEQuantidade[1];

        return quantidade > 0 ? total / quantidade : 0;
    }
}
