package com.syrion.hommunity_api.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.syrion.hommunity_api.api.dto.in.DtoZonaIn;
import com.syrion.hommunity_api.api.dto.out.DtoZonaOut;
import com.syrion.hommunity_api.common.dto.ApiResponse;

public interface SvcZona {
    ResponseEntity<ApiResponse> crearZona(DtoZonaIn dto);
    ResponseEntity<List<DtoZonaOut>> obtenerZonas();
}
