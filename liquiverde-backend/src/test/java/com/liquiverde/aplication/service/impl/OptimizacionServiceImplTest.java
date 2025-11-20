package com.liquiverde.aplication.service.impl;

import com.liquiverde.aplication.dto.OptimizarRequest;
import com.liquiverde.aplication.dto.OptimizarResponse;
import com.liquiverde.aplication.service.ProductoService;
import com.liquiverde.aplication.service.SostenibilidadService;
import com.liquiverde.domain.model.ProductoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OptimizacionServiceImplTest {

        ProductoService productoService;
        SostenibilidadService sostenibilidadService;
        OptimizacionServiceImpl optimizacionService;

        ProductoModel producto1, producto2, producto3;

        @BeforeEach
        void setUp() {
         MockitoAnnotations.openMocks(this);

            productoService = Mockito.mock(ProductoService.class);
            sostenibilidadService = Mockito.mock(SostenibilidadService.class);
            optimizacionService = new OptimizacionServiceImpl(productoService, sostenibilidadService);

            producto1 = ProductoModel.builder()
                    .id(1L)
                    .nombre("Producto A")
                    .precio(BigDecimal.valueOf(100))
                    .build();

            producto2 = ProductoModel.builder()
                    .id(2L)
                    .nombre("Producto B")
                    .precio(BigDecimal.valueOf(200))
                    .build();

            producto3 = ProductoModel.builder()
                    .id(3L)
                    .nombre("Producto C")
                    .precio(BigDecimal.valueOf(300))
                    .build();
        }

        @Test
        void should_throwException_when_requestIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                optimizacionService.optimizar(null);
            });
            assertEquals("Request no puede ser null", exception.getMessage());
        }

        @Test
        void should_throwException_when_productIdsEmpty() {
            OptimizarRequest req = new OptimizarRequest();
            req.setProductosIds(List.of());
            req.setPresupuesto(1000);

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                optimizacionService.optimizar(req);
            });
            assertEquals("Debe enviar al menos 1 ID de producto", exception.getMessage());
        }

        @Test
        void should_throwException_when_presupuestoIsZeroOrNegative() {
            OptimizarRequest req = new OptimizarRequest();
            req.setProductosIds(List.of(1L));
            req.setPresupuesto(0);

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                optimizacionService.optimizar(req);
            });
            assertEquals("El presupuesto debe ser mayor a 0", exception.getMessage());
        }

        @Test
        void should_returnOptimizedList() {
            OptimizarRequest req = new OptimizarRequest();
            req.setProductosIds(List.of(1L, 2L, 3L));
            req.setPresupuesto(300);

            when(productoService.obtenerPorId(1L)).thenReturn(producto1);
            when(productoService.obtenerPorId(2L)).thenReturn(producto2);
            when(productoService.obtenerPorId(3L)).thenReturn(producto3);

            when(sostenibilidadService.calcularScore(producto1)).thenReturn(80.0);
            when(sostenibilidadService.calcularScore(producto2)).thenReturn(90.0);
            when(sostenibilidadService.calcularScore(producto3)).thenReturn(70.0);

            OptimizarResponse response = optimizacionService.optimizar(req);

            assertNotNull(response);
            assertTrue(response.getSeleccionados().size() > 0);
            assertTrue(response.getAhorroEstimado() >= 0);
            assertTrue(response.getTotalPrecio() <= req.getPresupuesto());
        }
    }
