package com.syrion.hommunity_api.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syrion.hommunity_api.api.dto.in.DtoFamiliaIn;
import com.syrion.hommunity_api.api.dto.out.DtoFamiliaOut;
import com.syrion.hommunity_api.api.service.SvcFamilia;
import com.syrion.hommunity_api.common.dto.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/familia")
public class FamiliaController {

    @Autowired
    private SvcFamilia svcFamilia;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<ApiResponse> crearFamilia(@RequestBody DtoFamiliaIn familiaIn) {
        return svcFamilia.crearFamilia(familiaIn);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<ApiResponse> eliminarFamilia(@PathVariable Long id) {
        return svcFamilia.eliminarFamilia(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<DtoFamiliaOut> obtenerFamiliaPorId(@PathVariable Long id) {
        return svcFamilia.obtenerFamiliaPorId(id);
    }

    @GetMapping("/zona/{idZona}")
    public ResponseEntity<List<DtoFamiliaOut>> obtenerFamiliasPorZona(@Valid @PathVariable Long idZona) {
        
        return svcFamilia.obtenerFamiliasPorZona(idZona);
}
}
