package com.liquiverde.aplication.service;

import com.liquiverde.domain.model.ProductoModel;

public interface SostenibilidadService {
    double calcularScore(ProductoModel producto);

}
