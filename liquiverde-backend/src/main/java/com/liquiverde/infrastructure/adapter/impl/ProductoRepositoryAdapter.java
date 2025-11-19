package com.liquiverde.infrastructure.adapter.impl;

import com.liquiverde.aplication.mapper.ProductoEntityMapper;
import com.liquiverde.domain.model.ProductoModel;
import com.liquiverde.domain.repository.ProductoRepositoryPort;
import com.liquiverde.infrastructure.jpa.entity.ProductoEntity;
import com.liquiverde.infrastructure.jpa.repository.ProductoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final ProductoJpaRepository jpaRepository;
    private final ProductoEntityMapper mapper;

    public ProductoRepositoryAdapter(
            ProductoJpaRepository jpaRepository,
            ProductoEntityMapper mapper
    ) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductoModel save(ProductoModel model) {
        ProductoEntity entity = mapper.toEntity(model);
        return mapper.toModel(jpaRepository.save(entity));
    }

    @Override
    public ProductoModel update(ProductoModel model) {
        ProductoEntity entity = mapper.toEntity(model);
        return mapper.toModel(jpaRepository.save(entity));
    }

    @Override
    public Optional<ProductoModel> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toModel);
    }

    @Override
    public List<ProductoModel> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByCodigoBarras(String codigoBarras) {
        return jpaRepository.existsByCodigoBarras(codigoBarras);
    }

    @Override
    public Optional<ProductoModel> findByCodigoBarras(String codigoBarras) {
        return jpaRepository.findByCodigoBarras(codigoBarras)
                .map(mapper::toModel);
    }

    @Override
    public List<ProductoModel> buscarPorNombre(String nombre) {
        return jpaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toModel)
                .toList();
    }
}
