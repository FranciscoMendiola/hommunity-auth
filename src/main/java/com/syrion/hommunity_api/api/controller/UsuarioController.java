package com.syrion.hommunity_api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syrion.hommunity_api.api.dto.in.DtoUsuarioIn;
import com.syrion.hommunity_api.api.dto.out.DtoUsuarioOut;
import com.syrion.hommunity_api.api.service.SvcUsuario;
import com.syrion.hommunity_api.common.dto.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    SvcUsuario svUsuario;

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<DtoUsuarioOut> getUsuarioById(@PathVariable Long id) {
        return svUsuario.getUsuario(id);
    }

    // Registrar nuevo usuario
    @PostMapping
    public ResponseEntity<ApiResponse> createUsuario(@Valid @RequestBody DtoUsuarioIn in) {
        return svUsuario.createUsusario(in);
    }
}
