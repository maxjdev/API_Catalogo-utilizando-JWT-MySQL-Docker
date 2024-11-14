package br.com.catalog.models;

import br.com.catalog.models.abstract_entity.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_product")
public class Product extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "A descrição do produto é obrigatória")
    private String description;

    @Column(nullable = false, precision = 5, scale = 2)
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço do produto deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "O preço do produto deve ter no máximo 10 dígitos inteiros e 2 dígitos decimais")
    private BigDecimal price;

    @Column(nullable = false)
    @Min(value = 0, message = "A quantidade em estoque do produto não pode ser negativa")
    private int stockQuantity;
}
