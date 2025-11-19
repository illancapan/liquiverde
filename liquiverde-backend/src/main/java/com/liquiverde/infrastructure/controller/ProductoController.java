package com.liquiverde.infrastructure.controller;

import com.liquiverde.aplication.dto.ProductoResponse;
import com.liquiverde.aplication.mapper.ProductoDtoMapper;
import com.liquiverde.aplication.service.ProductoService;
import com.liquiverde.domain.model.ProductoModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/scan/{barcode}")
    public ResponseEntity<ProductoResponse> scan(@PathVariable String barcode) {
        ProductoModel model = productoService.buscarPorCodigoBarras(barcode);
        return ResponseEntity.ok(ProductoDtoMapper.toResponse(model));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponse>> buscarPorNombre(@RequestParam String nombre) {
        List<ProductoResponse> result = productoService.buscarPorNombre(nombre)
                .stream()
                .map(ProductoDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(result);
    }
}
