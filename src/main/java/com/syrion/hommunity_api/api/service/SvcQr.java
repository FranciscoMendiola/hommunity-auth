package com.syrion.hommunity_api.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.syrion.hommunity_api.api.dto.in.DtoCodigoIn;
import com.syrion.hommunity_api.api.dto.in.DtoCodigoResidenteIn;
import com.syrion.hommunity_api.api.entity.QR;
import com.syrion.hommunity_api.common.dto.ApiResponse;

public interface SvcQr {

    public ResponseEntity<List<QR>> getCodigos();
    public ResponseEntity<List<QR>> getCodigosActivos();
    public ResponseEntity<QR> getCodigo(Long id);
    public ResponseEntity<ApiResponse> createCodigo(DtoCodigoIn in);
    public ResponseEntity<ApiResponse> validar(Long id);
    public ResponseEntity<ApiResponse> createCodigoResidente(DtoCodigoResidenteIn in);

}
