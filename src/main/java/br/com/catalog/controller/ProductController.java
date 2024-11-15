package br.com.catalog.controller;

import br.com.catalog.controller.responses.PaginationResponse;
import br.com.catalog.controller.responses.Response;
import br.com.catalog.services.ICrudService;
import br.com.catalog.services.impls.dtos.ProductDTO;
import br.com.catalog.services.impls.dtos.UpdatedProductDTO;
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
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ICrudService<ProductDTO, UpdatedProductDTO> productService;

    public ProductController(ICrudService<ProductDTO, UpdatedProductDTO> productService) {
        this.productService = productService;
    }

    @Operation(summary = "Retorna todos os produtos em paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a paginação de produtos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos parâmetros")
    })
    @GetMapping("/getAll")
    public ResponseEntity<Response<PaginationResponse<ProductDTO>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10")int size) {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Products found successfully",
                productService.findAll(page, size)));
    }

    @Operation(summary = "Cria um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos parâmetros")
    })
    @PostMapping("/create")
    public ResponseEntity<Response<ProductDTO>> createProduct(@RequestBody @Valid ProductDTO dto) {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.CREATED.toString(),
                "Product created successfully",
                productService.create(dto)));
    }

    @Operation(summary = "Retorna um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/getById/{id}")
    public ResponseEntity<Response<ProductDTO>> getProductById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Product found successfully",
                productService.findById(id)));
    }

    @Operation(summary = "Retorna um produto pelo nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/getByName/{name}")
    public ResponseEntity<Response<ProductDTO>> getProductByName(@PathVariable String name) throws Exception {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Product found successfully",
                productService.findByName(name)));
    }

    @Operation(summary = "Atualiza um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos parâmetros"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Response<ProductDTO>> updateProduct(@PathVariable Long id,
                                           @RequestBody @Valid UpdatedProductDTO dto) throws Exception {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Product updated successfully",
                productService.update(id, dto)));
    }

    @Operation(summary = "Deleta logicamente um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws Exception {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
