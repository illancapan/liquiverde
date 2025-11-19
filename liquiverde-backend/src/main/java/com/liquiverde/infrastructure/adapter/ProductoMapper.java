package com.liquiverde.infrastructure.adapter;

import com.liquiverde.domain.model.ProductoModel;
import com.liquiverde.infrastructure.jpa.entity.ProductoEntity;

public class ProductoMapper {

    private ProductoMapper() {}

    public static ProductoModel toModel(ProductoEntity entity) {
        if (entity == null) return null;
        return ProductoModel.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .marca(entity.getMarca())
                .codigoBarras(entity.getCodigoBarras())
                .precio(entity.getPrecio())
                .puntajeSostenibilidad(entity.getPuntajeSostenibilidad())
                .build();
    }

    public static ProductoEntity toEntity(ProductoModel model) {
        if (model == null) return null;
        return ProductoEntity.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .marca(model.getMarca())
                .codigoBarras(model.getCodigoBarras())
                .precio(model.getPrecio())
                .puntajeSostenibilidad(model.getPuntajeSostenibilidad())
                .build();
    }

    public static void copyToEntity(ProductoModel model, ProductoEntity entity) {
        if (model == null || entity == null) return;
        entity.setNombre(model.getNombre());
        entity.setMarca(model.getMarca());
        entity.setCodigoBarras(model.getCodigoBarras());
        entity.setPrecio(model.getPrecio());
        entity.setPuntajeSostenibilidad(model.getPuntajeSostenibilidad());
    }

}
