package com.liquiverde.aplication.service.impl;

import com.liquiverde.aplication.service.SostenibilidadService;
import com.liquiverde.domain.model.ProductoModel;
import org.springframework.stereotype.Service;

@Service
public class SostenibilidadServiceImpl implements SostenibilidadService {


    @Override
    public double calcularScore(ProductoModel producto) {

        double ambiental = (producto.getPuntajeSostenibilidad().doubleValue() / 10.0) * 100.0;

        double precio = producto.getPrecio().doubleValue();
        double economico = 100.0 - Math.min(precio, 100_000.0) / 100_000.0 * 100.0;

        return (0.6 * ambiental) + (0.4 * economico);
    }
}
