package br.com.catalog.services.impls;

import static br.com.catalog.services.impls.messages.ErrorMessages.*;

import br.com.catalog.controller.responses.PaginationResponse;
import br.com.catalog.exceptions.business_exceptions.BrandAlreadyExistsException;
import br.com.catalog.exceptions.business_exceptions.IllegalPageSizeException;
import br.com.catalog.models.Brand;
import br.com.catalog.repositories.IBrandRepository;
import br.com.catalog.services.IAdmService;
import br.com.catalog.services.ICrudService;
import br.com.catalog.services.impls.dtos.BrandDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BrandServiceImpl implements ICrudService<BrandDTO, BrandDTO>, IAdmService<BrandDTO> {
    private final IBrandRepository brandRepository;
    private final Function<Brand, BrandDTO> brandToResponse;

    public BrandServiceImpl(IBrandRepository brandRepository, Function<Brand, BrandDTO> brandToResponse) {
        this.brandRepository = brandRepository;
        this.brandToResponse = brandToResponse;
    }

    @Transactional
    @Override
    public BrandDTO create(BrandDTO dto) {
        if (brandRepository.existsByNameIgnoreCase(dto.name()))
            throw new BrandAlreadyExistsException(BRAND_ALREADY_EXISTS);

        var brand = Brand.builder()
                .name(dto.name())
                .build();
        var save = brandRepository.save(brand);
        return brandToResponse.apply(save);
    }

    @Transactional
    @Override
    public BrandDTO update(Long id, BrandDTO dto) throws Exception {
        var brand = getActiveBrandByIdOrThrowException(id);

        if (brand.getName().equalsIgnoreCase(dto.name()))
            throw new BrandAlreadyExistsException(BRAND_ALREADY_EXISTS);

        if (dto.name() != null && !dto.name().isEmpty() &&
                !brandRepository.existsByNameIgnoreCase(dto.name()))
            brand.setName(dto.name());

        return brandToResponse.apply(brandRepository.save(brand));
    }

    @Override
    public BrandDTO findById(Long id) throws Exception {
        var brand = getActiveBrandByIdOrThrowException(id);
        return brandToResponse.apply(brand);
    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {
        var brand = getActiveBrandByIdOrThrowException(id);
        brand.delete();
        brandRepository.save(brand);
    }

    @Override
    public PaginationResponse<BrandDTO> findAll(int page, int size) {
        if (size < 1 || size > 20)
            throw new IllegalPageSizeException(ILLEGAL_PAGE_SIZE);

        var pageable = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        var brands = brandRepository
                .findAllActive(pageable)
                .map(brandToResponse);
        return PaginationResponse.pagination(brands);
    }

    @Override
    public BrandDTO findByName(String name) {
        var brand = brandRepository.findActiveByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException(BRAND_NOT_FOUND));
        return brandToResponse.apply(brand);
    }

    /*
    * ADM
    */
    @Transactional
    @Override
    public void fullDelete(Long id) throws Exception {
        var brand = getBrandByIdOrElseThrow(id);
        brandRepository.deleteById(brand.getId());
    }

    @Transactional
    @Override
    public void disable(Long id) throws Exception {
        var brand = getActiveBrandByIdOrThrowException(id);
        brand.disable();
        brandRepository.save(brand);
    }

    @Transactional
    @Override
    public BrandDTO activate(Long id) throws Exception {
        var brand = getBrandByIdOrElseThrow(id);
        brand.activate();
        return brandToResponse.apply(brandRepository.save(brand));
    }

    /*
     * PRIVATE METHODS
     */
    private Brand getActiveBrandByIdOrThrowException(Long id) {
        return brandRepository.findActiveById(id)
                .orElseThrow(() -> new EntityNotFoundException(BRAND_NOT_FOUND));
    }

    private Brand getBrandByIdOrElseThrow(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BRAND_NOT_FOUND));
    }
}
