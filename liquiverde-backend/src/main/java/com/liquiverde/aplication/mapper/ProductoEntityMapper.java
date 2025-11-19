package com.liquiverde.aplication.mapper;

import com.liquiverde.domain.model.ProductoModel;
import com.liquiverde.infrastructure.jpa.entity.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoEntityMapper {

    public ProductoModel toModel(ProductoEntity entity) {
        if (entity == null) {
            return null;
        }

        return ProductoModel.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .marca(entity.getMarca())
                .codigoBarras(entity.getCodigoBarras())
                .precio(entity.getPrecio())
                .puntajeSostenibilidad(entity.getPuntajeSostenibilidad())
                .build();
    }

    public ProductoEntity toEntity(ProductoModel model) {
        if (model == null) {
            return null;
        }

        return ProductoEntity.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .marca(model.getMarca())
                .codigoBarras(model.getCodigoBarras())
                .precio(model.getPrecio())
                .puntajeSostenibilidad(model.getPuntajeSostenibilidad())
                .build();
    }
}
