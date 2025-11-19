package com.liquiverde.aplication.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OptimizarResponse {
    private List<ProductoResponse> seleccionados;
    private double totalPrecio;
    private double ahorroEstimado;
}
