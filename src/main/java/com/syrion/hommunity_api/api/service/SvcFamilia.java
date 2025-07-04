package com.syrion.hommunity_api.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.syrion.hommunity_api.api.dto.in.DtoFamiliaIn;
import com.syrion.hommunity_api.api.dto.in.DtoUsuarioRegistradorIn;
import com.syrion.hommunity_api.api.dto.out.DtoFamiliaOut;
import com.syrion.hommunity_api.common.dto.ApiResponse;

public interface SvcFamilia {
    ResponseEntity<DtoFamiliaOut> obtenerFamiliaPorId(Long id);
    ResponseEntity<List<DtoFamiliaOut>> obtenerFamiliasPorZona(Long idZona);
    ResponseEntity<ApiResponse> crearFamilia(DtoFamiliaIn familiaIn);
    ResponseEntity<ApiResponse> eliminarFamilia(Long idFamilia);

    ResponseEntity<ApiResponse> updateUsuarioRegistrador(Long idFamilia, DtoUsuarioRegistradorIn in);
}
