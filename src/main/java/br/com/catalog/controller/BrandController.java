package br.com.catalog.controller;

import br.com.catalog.controller.responses.PaginationResponse;
import br.com.catalog.controller.responses.Response;
import br.com.catalog.services.ICrudService;
import br.com.catalog.services.impls.dtos.BrandDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {
    private final ICrudService<BrandDTO, BrandDTO> brandService;

    public BrandController(ICrudService<BrandDTO, BrandDTO> service) {
        this.brandService = service;
    }

    @Operation(summary = "Retorna todas as marcas em paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a paginação de marcas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos parâmetros")
    })
    @GetMapping("/getAll")
    public ResponseEntity<Response<PaginationResponse<BrandDTO>>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Brands found successfully",
                brandService.findAll(page, size)));
    }

    @Operation(summary = "Busca uma marca pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    @GetMapping("/getById/{id}")
    public ResponseEntity<Response<BrandDTO>> findBrandById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Brand found successfully",
                brandService.findById(id)));
    }

    @Operation(summary = "Busca uma marca pelo nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    @GetMapping("/getByName/{name}")
    public ResponseEntity<Response<BrandDTO>> findBrandByName(@PathVariable String name) throws Exception {
        return  ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Brand found successfully",
                brandService.findByName(name)));
    }

    @Operation(summary = "Cria uma nova marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/create")
    public ResponseEntity<Response<BrandDTO>> createbrand(@RequestBody @Valid BrandDTO dto) {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.CREATED.toString(),
                "Brand created successfully",
                brandService.create(dto)));
    }

    @Operation(summary = "Atualiza uma marca existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Response<BrandDTO>> updateBrand(@PathVariable Long id,
                                                          @RequestBody @Valid BrandDTO dto) throws Exception {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Brand updated successfully",
                brandService.update(id, dto)));
    }

    @Operation(summary = "Deleta uma marca logicamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Marca deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) throws Exception {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
