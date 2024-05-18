package com.pessoa.rest;


import com.pessoa.dto.PessoaDTO;
import com.pessoa.service.PessoaServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "Endpoints para gerenciar entidade Pessoa.")
public class PessoaRestImp {


    @Autowired
    private PessoaServiceImp service;

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar pessoa pelo ID", description = "Procura uma pessoa pelo id. Caso não encontre, retorna um NOT_FOUND",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PessoaDTO findById(@PathVariable("cpf") Long cpf) {
        return service.findById(cpf);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos as pessoas", description = "Busca todas as pessoas cadastradas no Banco de Dados.",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                            @Content (
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = PessoaDTO.class
                                            )
                                    )
                            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<PessoaDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persiste uma nova pessoa no Banco de Dados", description = "Persiste uma nova pessoa no Banco de Dados, caso ele não encontre outro com o mesmo ID.",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = PessoaDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PessoaDTO create(@RequestBody PessoaDTO pessoaDTO) {
        return service.create(pessoaDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de uma pessoa", description = "Busca a pessoa, atualiza, persiste e retorna a pessoa atualizada.",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = PessoaDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PessoaDTO update(@RequestBody PessoaDTO pessoaDTO) {
        return service.update(pessoaDTO);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Apaga uma pessoa através do ID", description = "Busca uma pessoa pelo ID fornecido e se encontrar, apaga",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable("cpf") Long cpf) {
        return service.delete(cpf);
    }
}
