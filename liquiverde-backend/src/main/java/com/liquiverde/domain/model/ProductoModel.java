package com.liquiverde.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductoModel {
    private Long id;
    private String nombre;
    private String marca;
    private String codigoBarras;
    private BigDecimal precio;
    private BigDecimal puntajeSostenibilidad;
}
