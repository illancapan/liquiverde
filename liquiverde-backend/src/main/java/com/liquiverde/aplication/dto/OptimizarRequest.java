package com.liquiverde.aplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OptimizarRequest {
    private List<Long> productosIds;
    private double presupuesto;
}