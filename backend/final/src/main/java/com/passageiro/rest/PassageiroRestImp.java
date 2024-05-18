package com.passageiro.rest;

import com.passageiro.dto.PassageiroDTO;
import com.passageiro.service.PassageiroServiceImp;
import com.passageiro.dto.PassageiroDTO;
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
@RequestMapping("/passageiros")
@Tag(name = "Passageiros", description = "Endpoints para gerenciar entidade Passageiro.")
public class PassageiroRestImp {
    
    @Autowired
    private PassageiroServiceImp service;

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar passageiro pelo CPF", description = "Procura um passageiro pelo id. Caso não encontre, retorna um NOT_FOUND",
            tags = {"Passageiros"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = PassageiroDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PassageiroDTO findById(@PathVariable("cpf") Long cpf) {
        return service.findById(cpf);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os passageiros", description = "Busca todas os passageiros cadastrados no Banco de Dados.",
            tags = {"Passageiros"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                            @Content (
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = PassageiroDTO.class
                                            )
                                    )
                            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<PassageiroDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persiste um novo passageiro no Banco de Dados", description = "Persiste um novo passageiro no Banco de Dados, caso ele não encontre outro com o mesmo ID.",
            tags = {"Passageiros"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = PassageiroDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PassageiroDTO create(@RequestBody PassageiroDTO passageiroDTO) {
        return service.create(passageiroDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um passageiro", description = "Busca o passageiro, atualiza, persiste e retorna o passageiro atualizado.",
            tags = {"Passageiros"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = PassageiroDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PassageiroDTO update(@RequestBody PassageiroDTO passageiroDTO) {
        return service.update(passageiroDTO);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Apaga um passageiro através do ID", description = "Busca um passageiro pelo ID fornecido e se encontrar, apaga",
            tags = {"Passageiros"},
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
