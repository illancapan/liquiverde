package com.liquiverde.aplication.mapper;

import com.liquiverde.aplication.dto.ProductoRequest;
import com.liquiverde.aplication.dto.ProductoResponse;
import com.liquiverde.domain.model.ProductoModel;

public final class ProductoDtoMapper {
    private ProductoDtoMapper() {}

    public static ProductoModel toModel(ProductoRequest request) {
        if (request == null) return null;
        return ProductoModel.builder()
                .nombre(request.getNombre())
                .marca(request.getMarca())
                .codigoBarras(request.getCodigoBarras())
                .precio(request.getPrecio())
                .puntajeSostenibilidad(request.getPuntajeSostenibilidad())
                .build();
    }

    public static ProductoResponse toResponse(ProductoModel model) {
        if (model == null) return null;
        return ProductoResponse.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .marca(model.getMarca())
                .codigoBarras(model.getCodigoBarras())
                .precio(model.getPrecio())
                .puntajeSostenibilidad(model.getPuntajeSostenibilidad())
                .build();
    }
}
