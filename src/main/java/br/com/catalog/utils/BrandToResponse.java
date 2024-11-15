package br.com.catalog.utils;

import br.com.catalog.models.Brand;
import br.com.catalog.services.impls.dtos.BrandDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BrandToResponse implements Function<Brand, BrandDTO> {
    @Override
    public BrandDTO apply(Brand brand) {
        return new BrandDTO(brand.getName());
    }
}

