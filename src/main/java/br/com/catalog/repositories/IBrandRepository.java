package br.com.catalog.repositories;

import br.com.catalog.models.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT b FROM Brand b WHERE LOWER(b.name) = LOWER(:name) AND b.status = 'ACTIVE'")
    Optional<Brand> findActiveByNameIgnoreCase(String name);

    @Query("SELECT b FROM Brand b WHERE b.id = :id AND b.status = 'ACTIVE'")
    Optional<Brand> findActiveById(Long id);

    @Query("SELECT b FROM Brand b WHERE b.status = 'ACTIVE'")
    Page<Brand> findAllActive(Pageable pageable);
}