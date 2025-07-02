package com.syrion.hommunity_api.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.syrion.hommunity_api.api.dto.in.DtoFamiliaIn;
import com.syrion.hommunity_api.api.dto.out.DtoFamiliaOut;
import com.syrion.hommunity_api.api.service.SvcFamilia;
import com.syrion.hommunity_api.common.dto.ApiResponse;

@RestController
@RequestMapping("/familia")
public class FamiliaController {

    private final SvcFamilia svcFamilia;

    public FamiliaController(SvcFamilia svcFamilia) {
        this.svcFamilia = svcFamilia;
    }

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
}
