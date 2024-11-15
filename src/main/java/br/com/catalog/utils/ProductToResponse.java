package br.com.catalog.utils;

import br.com.catalog.models.Product;
import br.com.catalog.services.impls.dtos.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductToResponse implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .brand(product.getBrand().getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuanitity(product.getStockQuantity())
                .build();
    }
}
