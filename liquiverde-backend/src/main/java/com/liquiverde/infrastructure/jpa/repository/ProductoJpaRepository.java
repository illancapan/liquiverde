package com.liquiverde.infrastructure.jpa.repository;

import com.liquiverde.infrastructure.jpa.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {

    boolean existsByCodigoBarras(String codigoBarras);

    Optional<ProductoEntity> findByCodigoBarras(String codigoBarras);
    List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);

}
