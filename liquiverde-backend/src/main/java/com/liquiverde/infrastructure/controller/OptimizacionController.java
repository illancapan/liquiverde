package com.liquiverde.infrastructure.controller;

import com.liquiverde.aplication.dto.OptimizarRequest;
import com.liquiverde.aplication.dto.OptimizarResponse;
import com.liquiverde.aplication.service.OptimizacionService;
import com.liquiverde.aplication.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listas")
public class OptimizacionController {

    private final OptimizacionService optimizacionService;

    public OptimizacionController(OptimizacionService optimizacionService) {
        this.optimizacionService = optimizacionService;
    }

    @PostMapping("/optimizar")
    public ResponseEntity<OptimizarResponse> optimizar(@RequestBody OptimizarRequest req) {
        return ResponseEntity.ok(optimizacionService.optimizar(req));
    }
}


