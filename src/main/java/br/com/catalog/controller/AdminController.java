package br.com.catalog.controller;

import br.com.catalog.controller.responses.Response;
import br.com.catalog.services.IAdmService;
import br.com.catalog.services.impls.dtos.BrandDTO;
import br.com.catalog.services.impls.dtos.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final IAdmService<ProductDTO> admProductService;
    private final IAdmService<BrandDTO> admBrandService;

    public AdminController(IAdmService<ProductDTO> admProductService,
                           IAdmService<BrandDTO> admBrandService) {
        this.admProductService = admProductService;
        this.admBrandService = admBrandService;
    }

    /*
     * Brand
     */
    @Operation(summary = "Deleta uma marca permanentemente (ADM)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Marca deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    @DeleteMapping("/brand/fullDelete/{id}")
    public ResponseEntity<Response<Void>> fullDeleteBrand(@PathVariable Long id) throws Exception {
        admBrandService.fullDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Desativa uma marca (ADM)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca desativada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    @PutMapping("/brand/disable/{id}")
    public ResponseEntity<Response<Void>> disableBrand(@PathVariable Long id) throws Exception {
        admBrandService.disable(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Ativa uma marca desativada (ADM)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca ativada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    @PutMapping("/brand/activate/{id}")
    public ResponseEntity<Response<BrandDTO>> activateBrand(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Brand activated successfully",
                admBrandService.activate(id)));
    }

    /*
     * Product
     */
    @Operation(summary = "Exclui permanentemente um produto (ADM)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído permanentemente"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/product/fullDelete/{id}")
    public ResponseEntity<Response<Void>> fullDeleteProduct(@PathVariable Long id) throws Exception {
        admProductService.fullDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Desativa um produto (ADM)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/product/disable/{id}")
    public ResponseEntity<Response<Void>> disableProduct(@PathVariable Long id) throws Exception {
        admProductService.disable(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Ativa um produto (ADM)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto ativado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/product/activate/{id}")
    public ResponseEntity<Response<ProductDTO>> activateProduct(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "Product activated successfully",
                admProductService.activate(id)));
    }
}
