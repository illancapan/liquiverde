package com.liquiverde.domain.repository;

import com.liquiverde.domain.model.ProductoModel;

import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {

    ProductoModel save(ProductoModel producto);

    ProductoModel update(ProductoModel producto);

    Optional<ProductoModel> findById(Long id);

    List<ProductoModel> findAll();

    void deleteById(Long id);

    boolean existsByCodigoBarras(String codigoBarras);

    Optional<ProductoModel> findByCodigoBarras(String codigoBarras);

    List<ProductoModel> buscarPorNombre(String nombre);
}
