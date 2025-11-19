package com.liquiverde.aplication.service;

import com.liquiverde.aplication.dto.OptimizarRequest;
import com.liquiverde.aplication.dto.OptimizarResponse;
import com.liquiverde.domain.model.ProductoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OptimizacionService {
    OptimizarResponse optimizar(OptimizarRequest request);


}
