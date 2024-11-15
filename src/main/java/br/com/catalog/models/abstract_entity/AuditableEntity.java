package br.com.catalog.models.abstract_entity;

import br.com.catalog.models.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Status status;

    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(nullable = false)
    protected LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.status = Status.ACTIVE;
        this.updatedAt = LocalDateTime.now();
        onStatusChange(Status.ACTIVE);
    }

    public void disable() {
        this.status = Status.INACTIVE;
        this.updatedAt = LocalDateTime.now();
        onStatusChange(Status.INACTIVE);
    }

    public void delete() {
        this.status = Status.DELETED;
        this.updatedAt = LocalDateTime.now();
        onStatusChange(Status.DELETED);
    }

    protected void onStatusChange(Status newStatus) {
        // TODO Implementar as mudanças necessárias quando o status mudar
    };
}
