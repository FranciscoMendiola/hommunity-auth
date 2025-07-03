package com.syrion.hommunity_api.api.service;

import org.springframework.http.ResponseEntity;

import com.syrion.hommunity_api.api.dto.in.DtoUsuarioIn;
import com.syrion.hommunity_api.api.dto.out.DtoUsuarioOut;
import com.syrion.hommunity_api.common.dto.ApiResponse;

public interface SvcUsuario {

    public ResponseEntity<DtoUsuarioOut> getUsuario(Long id);
    public ResponseEntity<ApiResponse> createUsusario(DtoUsuarioIn in);
    public ResponseEntity<ApiResponse> deleteUsuario(Long id);
    public ResponseEntity<ApiResponse> updateUsuario(Long id, DtoUsuarioIn in);
}