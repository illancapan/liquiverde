package com.liquiverde.aplication.service.impl;

import com.liquiverde.aplication.dto.OptimizarRequest;
import com.liquiverde.aplication.dto.OptimizarResponse;
import com.liquiverde.aplication.mapper.ProductoDtoMapper;
import com.liquiverde.aplication.service.OptimizacionService;
import com.liquiverde.aplication.service.ProductoService;
import com.liquiverde.aplication.service.SostenibilidadService;
import com.liquiverde.domain.model.ProductoModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class OptimizacionServiceImpl implements OptimizacionService {

    private final ProductoService productoService;
    private final SostenibilidadService sostenibilidadService;

    public OptimizacionServiceImpl(
            ProductoService productoService,
            SostenibilidadService sostenibilidadService
    ) {
        this.productoService = productoService;
        this.sostenibilidadService = sostenibilidadService;
    }

    @Override
    public OptimizarResponse optimizar(OptimizarRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("Request no puede ser null");
        }

        if (request.getProductosIds() == null || request.getProductosIds().isEmpty()) {
            throw new IllegalArgumentException("Debe enviar al menos 1 ID de producto");
        }

        if (request.getPresupuesto() <= 0) {
            throw new IllegalArgumentException("El presupuesto debe ser mayor a 0");
        }

        List<ProductoModel> productos = request.getProductosIds().stream()
                .map(productoService::obtenerPorId)
                .toList();

        List<ProductoModel> seleccionados = optimizarLista(productos, request.getPresupuesto());

        double total = seleccionados.stream()
                .mapToDouble(p -> p.getPrecio().doubleValue())
                .sum();

        double original = productos.stream()
                .mapToDouble(p -> p.getPrecio().doubleValue())
                .sum();

        return OptimizarResponse.builder()
                .seleccionados(
                        seleccionados.stream()
                                .map(ProductoDtoMapper::toResponse)
                                .toList()
                )
                .totalPrecio(total)
                .ahorroEstimado(original - total)
                .build();
    }


    private List<ProductoModel> optimizarLista(List<ProductoModel> productos, double presupuesto) {

        List<ProductoModel> ordenados = productos.stream()
                .sorted(Comparator.comparingDouble(
                        p -> - (sostenibilidadService.calcularScore(p) / p.getPrecio().doubleValue())
                ))
                .toList();

        double total = 0;
        List<ProductoModel> seleccionados = new ArrayList<>();

        for (ProductoModel p : ordenados) {
            double precio = p.getPrecio().doubleValue();
            if (total + precio <= presupuesto) {
                seleccionados.add(p);
                total += precio;
            }
        }

        return seleccionados;
    }
}
