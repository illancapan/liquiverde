package com.liquiverde.aplication.service;

import com.liquiverde.domain.model.ProductoModel;

import java.util.List;

public interface ProductoService {

    ProductoModel crear(ProductoModel producto);

    ProductoModel actualizar(ProductoModel producto);

    ProductoModel obtenerPorId(Long id);

    List<ProductoModel> listarTodos();

    void eliminar(Long id);

    ProductoModel buscarPorCodigoBarras(String codigoBarras);

    List<ProductoModel> buscarPorNombre(String nombre);
}
