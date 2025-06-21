package com.syrion.hommunity_api.api.service;

import org.springframework.http.ResponseEntity;

import com.syrion.hommunity_api.api.dto.DtoUsuarioIn;
import com.syrion.hommunity_api.api.entity.Usuario;
import com.syrion.hommunity_api.common.dto.ApiResponse;

public interface SvcUsuario {

    public ResponseEntity<Usuario> getUsuario(Long id);
    public ResponseEntity<ApiResponse> createUsusario(DtoUsuarioIn in);
    
}