package br.com.erp.apierp.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class EntityModel {
    private LocalDateTime entradaData;
    private LocalDateTime atualizacaoData;

    @PrePersist
    public void prePersist() {
        this.entradaData = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizacaoData = LocalDateTime.now();
    }
}
