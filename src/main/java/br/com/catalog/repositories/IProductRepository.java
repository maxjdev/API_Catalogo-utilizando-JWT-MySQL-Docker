package br.com.catalog.repositories;

import br.com.catalog.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name) AND p.status = 'ACTIVE'")
    Optional<Product> findActiveByNameIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.status = 'ACTIVE'")
    Optional<Product> findActiveById(Long id);

    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE'")
    Page<Product> findAllActive(Pageable pageable);
}
