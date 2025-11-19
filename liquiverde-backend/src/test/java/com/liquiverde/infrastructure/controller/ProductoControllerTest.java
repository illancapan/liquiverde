package com.liquiverde.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liquiverde.aplication.dto.ProductoRequest;
import com.liquiverde.aplication.service.ProductoService;
import com.liquiverde.domain.model.ProductoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductoControllerTest {
    private MockMvc mockMvc;
    private ProductoService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        service = Mockito.mock(ProductoService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductoController(service)).build();
    }

    @Test
    void should_create_product() throws Exception {
        ProductoRequest req = ProductoRequest.builder()
                .nombre("Jabón")
                .codigoBarras("123")
                .precio(BigDecimal.valueOf(2.5))
                .puntajeSostenibilidad(BigDecimal.valueOf(7.8))
                .build();

        ProductoModel created = ProductoModel.builder()
                .id(1L)
                .nombre("Jabón")
                .codigoBarras("123")
                .precio(BigDecimal.valueOf(2.5))
                .puntajeSostenibilidad(BigDecimal.valueOf(7.8))
                .build();

        Mockito.when(service.crear(Mockito.any())).thenReturn(created);

        mockMvc.perform(post("/productos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Jabón"));
    }
}