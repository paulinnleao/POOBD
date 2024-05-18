package com.tipoPgto.rest;

import com.tipoPgto.dto.TipoPgtoDTO;
import com.tipoPgto.service.TipoPgtoServiceImp;
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
@RequestMapping("/tipos-pagamentos")
@Tag(name = "Tipos Pagamentos", description = "Endpoints para gerenciar entidade TipoPgto.")
public class TipoPgtoRestImp {


    @Autowired
    private TipoPgtoServiceImp service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os tipos de pagamentos", description = "Busca todos os tipos de pagamentos no Banco de Dados.",
            tags = {"Tipos Pagamentos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                            @Content (
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = TipoPgtoDTO.class
                                            )
                                    )
                            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<TipoPgtoDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar tipo de pagamento pelo ID", description = "Procura um tipo de pagamento pelo ID. Caso não encontre, retorna um NOT_FOUND",
            tags = {"Tipos Pagamentos"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = TipoPgtoDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public TipoPgtoDTO findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persiste um novo tipo de pagamento no Banco de Dados", description = "Persiste um novo tipo de pagamento no Banco de Dados, caso ele não encontre outro com o mesmo ID.",
            tags = {"Tipos Pagamentos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = TipoPgtoDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public TipoPgtoDTO create(@RequestBody TipoPgtoDTO tipoPgtoDTO) {
        return service.create(tipoPgtoDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um tipo de pagamento", description = "Busca o tipo de pagamento, atualiza, persiste e retorna o tipo de pagamento atualizado.",
            tags = {"Tipos Pagamentos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = TipoPgtoDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public TipoPgtoDTO update(@RequestBody TipoPgtoDTO tipoPgtoDTO) {
        return service.update(tipoPgtoDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga um tipo de pagamento através do ID", description = "Busca um tipo de pagamento pelo ID fornecido e se o encontrar, o apaga",
            tags = {"Tipos Pagamentos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return service.delete(id);
    }
}
