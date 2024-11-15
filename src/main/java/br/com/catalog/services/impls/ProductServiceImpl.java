package br.com.catalog.services.impls;

import static br.com.catalog.services.impls.messages.ErrorMessages.*;

import br.com.catalog.controller.responses.PaginationResponse;
import br.com.catalog.exceptions.business_exceptions.IllegalPageSizeException;
import br.com.catalog.exceptions.business_exceptions.ProductAlreadyExistsException;
import br.com.catalog.models.Product;
import br.com.catalog.repositories.IBrandRepository;
import br.com.catalog.repositories.IProductRepository;
import br.com.catalog.services.IAdmService;
import br.com.catalog.services.ICrudService;
import br.com.catalog.services.impls.dtos.ProductDTO;
import br.com.catalog.services.impls.dtos.UpdatedProductDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductServiceImpl implements ICrudService<ProductDTO, UpdatedProductDTO>, IAdmService<ProductDTO> {
    private final IProductRepository productRepository;
    private final IBrandRepository brandRepository;
    private final Function<Product, ProductDTO> productToResponse;

    public ProductServiceImpl(IProductRepository productRepository,
                              IBrandRepository brandRepository,
                              Function<Product, ProductDTO> productToResponse) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.productToResponse = productToResponse;
    }

    @Transactional
    @Override
    public ProductDTO create(ProductDTO dto) {
        if (productRepository.existsByNameIgnoreCase(dto.name()))
            throw new ProductAlreadyExistsException(PRODUCT_ALREADY_EXISTS);

        var brand = brandRepository.findActiveByNameIgnoreCase(dto.brand())
                .orElseThrow(() -> new EntityNotFoundException(BRAND_NOT_FOUND));

        var product = Product.builder()
                .name(dto.name())
                .brand(brand)
                .description(dto.description())
                .price(dto.price())
                .stockQuantity(dto.stockQuanitity())
                .build();

        var save = productRepository.save(product);

        return productToResponse.apply(save);
    }

    @Override
    public ProductDTO findById(Long id) {
        var product = getActiveProductByIdOrThrowException(id);
        return productToResponse.apply(product);
    }

    @Override
    public ProductDTO findByName(String name) {
        var product = productRepository.findActiveByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND));
        return productToResponse.apply(product);
    }

    @Transactional
    @Override
    public ProductDTO update(Long id, UpdatedProductDTO dto) {
        var product = getActiveProductByIdOrThrowException(id);

        Optional.ofNullable(dto.name())
                .filter(name -> !name.isEmpty())
                .ifPresent(product::setName);
        Optional.ofNullable(dto.description())
                .filter(desc -> !desc.isEmpty())
                .ifPresent(product::setDescription);
        Optional.of(dto.stockQuantity())
                .filter(stock -> stock >= 0)
                .ifPresent(product::setStockQuantity);
        Optional.ofNullable(dto.price())
                .ifPresent(product::setPrice);

        if (dto.stockQuantity() == 0) product.disable();

        var saved = productRepository.save(product);

        return productToResponse.apply(saved);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var product = getActiveProductByIdOrThrowException(id);
        product.delete();
        productRepository.save(product);
    }

    @Override
    public PaginationResponse<ProductDTO> findAll(int page, int size) {
        if (size < 1 || size > 20)
            throw new IllegalPageSizeException(ILLEGAL_PAGE_SIZE);

        var pageable = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        var products = productRepository
                .findAllActive(pageable)
                .map(productToResponse);
        return PaginationResponse.pagination(products);
    }

    /*
    * ADM
    */
    @Transactional
    @Override
    public void disable(Long id) {
        var product = getActiveProductByIdOrThrowException(id);
        product.disable();
        productRepository.save(product);
    }

    @Transactional
    @Override
    public ProductDTO activate(Long id) {
        var product = getProductByIdOrThrowException(id);
        product.activate();
        return productToResponse.apply(productRepository.save(product));
    }

    @Transactional
    @Override
    public void fullDelete(Long id) {
        var product = getProductByIdOrThrowException(id);
        productRepository.deleteById(product.getId());
    }

    /*
     * PRIVATE METHODS
     */
    private Product getActiveProductByIdOrThrowException(Long id) {
        return productRepository.findActiveById(id)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND));
    }

    private Product getProductByIdOrThrowException(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND));
    }
}
