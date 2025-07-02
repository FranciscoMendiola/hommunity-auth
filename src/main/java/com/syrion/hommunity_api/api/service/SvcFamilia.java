package com.syrion.hommunity_api.api.service;

import org.springframework.http.ResponseEntity;

import com.syrion.hommunity_api.api.dto.in.DtoFamiliaIn;
import com.syrion.hommunity_api.api.dto.out.DtoFamiliaOut;
import com.syrion.hommunity_api.common.dto.ApiResponse;

public interface SvcFamilia {
    ResponseEntity<ApiResponse> crearFamilia(DtoFamiliaIn familiaIn);
    ResponseEntity<ApiResponse> eliminarFamilia(Long idFamilia);
    ResponseEntity<DtoFamiliaOut> obtenerFamiliaPorId(Long id);
}
