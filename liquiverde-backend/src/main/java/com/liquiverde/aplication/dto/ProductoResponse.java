package com.liquiverde.aplication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductoResponse {
    private Long id;
    private String nombre;
    private String marca;
    private String codigoBarras;
    private BigDecimal precio;
    private BigDecimal puntajeSostenibilidad;
}
