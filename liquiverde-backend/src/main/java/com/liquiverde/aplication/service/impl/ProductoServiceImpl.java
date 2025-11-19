package com.liquiverde.aplication.service.impl;

import com.liquiverde.aplication.service.ProductoService;
import com.liquiverde.domain.model.ProductoModel;
import com.liquiverde.domain.repository.ProductoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepositoryPort repository;

    public ProductoServiceImpl(ProductoRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public ProductoModel buscarPorCodigoBarras(String codigoBarras) {

        if (codigoBarras == null || codigoBarras.isBlank()) {
            throw new IllegalArgumentException("El código de barras no puede estar vacío.");
        }

        return repository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe producto con código de barras: " + codigoBarras
                ));
    }

    @Override
    public List<ProductoModel> buscarPorNombre(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar un nombre para buscar.");
        }

        return repository.buscarPorNombre(nombre);
    }

    @Override
    public ProductoModel crear(ProductoModel producto) {
        if (producto.getCodigoBarras() != null && repository.existsByCodigoBarras(producto.getCodigoBarras())) {
            throw new IllegalArgumentException("Código de barras ya existe");
        }
        return repository.save(producto);
    }

    @Override
    public ProductoModel actualizar(ProductoModel producto) {
        // Validaciones básicas
        if (producto.getId() == null) throw new IllegalArgumentException("Id requerido para actualizar");
        // optional: validar uniqueness (si se cambió codigoBarras)
        return repository.update(producto);
    }

    @Override
    public ProductoModel obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }

    @Override
    public List<ProductoModel> listarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
