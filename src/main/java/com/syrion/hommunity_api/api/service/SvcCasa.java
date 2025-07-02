package com.syrion.hommunity_api.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.syrion.hommunity_api.api.dto.in.DtoCasaIn;
import com.syrion.hommunity_api.api.entity.Casa;
import com.syrion.hommunity_api.api.dto.out.DtoCasaOut;
import com.syrion.hommunity_api.common.dto.ApiResponse;


public interface SvcCasa {
    ResponseEntity<ApiResponse> crearCasa(DtoCasaIn casaIn);
    ResponseEntity<ApiResponse> eliminarCasa(Long idCasa);
    ResponseEntity<List<DtoCasaOut>> buscarPorZona(Long idZona);
    ResponseEntity<DtoCasaOut> obtenerCasaPorId(Long id);
}
