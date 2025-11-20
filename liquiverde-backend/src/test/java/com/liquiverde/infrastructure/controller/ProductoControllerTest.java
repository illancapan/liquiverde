package com.liquiverde.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liquiverde.aplication.service.ProductoService;
import com.liquiverde.domain.model.ProductoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductoControllerTest {

        private MockMvc mockMvc;
        private ProductoService service;
        private final ObjectMapper objectMapper = new ObjectMapper();

        private ProductoModel producto1, producto2;

        @BeforeEach
        void setup() {
            service = Mockito.mock(ProductoService.class);
            mockMvc = MockMvcBuilders.standaloneSetup(new ProductoController(service)).build();

            producto1 = ProductoModel.builder()
                    .id(1L)
                    .nombre("Jabón ecológico")
                    .marca("EcoPlus")
                    .codigoBarras("1111111111111")
                    .precio(BigDecimal.valueOf(4.99))
                    .build();

            producto2 = ProductoModel.builder()
                    .id(2L)
                    .nombre("Botella reutilizable")
                    .marca("GreenLife")
                    .codigoBarras("2222222222222")
                    .precio(BigDecimal.valueOf(9.99))
                    .build();
        }

        @Test
        void should_scan_product_by_barcode() throws Exception {
            Mockito.when(service.buscarPorCodigoBarras("1111111111111")).thenReturn(producto1);

            mockMvc.perform(get("/productos/scan/1111111111111")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.nombre").value("Jabón ecológico"))
                    .andExpect(jsonPath("$.marca").value("EcoPlus"))
                    .andExpect(jsonPath("$.codigoBarras").value("1111111111111"))
                    .andExpect(jsonPath("$.precio").value(4.99));
        }

        @Test
        void should_search_products_by_name() throws Exception {
            List<ProductoModel> productos = Arrays.asList(producto1, producto2);
            Mockito.when(service.buscarPorNombre("Botella")).thenReturn(productos);

            mockMvc.perform(get("/productos/buscar")
                                    .param("nombre", "Botella")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[0].id").value(1L))
                    .andExpect(jsonPath("$[1].id").value(2L));
        }
    }