package br.com.catalog.models;

import br.com.catalog.models.abstract_entity.AuditableEntity;
import br.com.catalog.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_brand")
public class Brand extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> products;

    @Override
    protected void onStatusChange(Status newStatus) {
        this.products.forEach(product -> product.setStatus(newStatus));
    }
}
