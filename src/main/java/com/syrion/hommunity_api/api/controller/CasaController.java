package com.syrion.hommunity_api.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.syrion.hommunity_api.api.dto.in.DtoCasaIn;
import com.syrion.hommunity_api.api.dto.out.DtoCasaOut;
import com.syrion.hommunity_api.api.service.SvcCasa;
import com.syrion.hommunity_api.common.dto.ApiResponse;

@RestController
@RequestMapping("/casa")
public class CasaController {

    private final SvcCasa svcCasa;

    public CasaController(SvcCasa svcCasa) {
        this.svcCasa = svcCasa;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<ApiResponse> crearCasa(@RequestBody DtoCasaIn casaIn) {
        return svcCasa.crearCasa(casaIn);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<ApiResponse> eliminarCasa(@PathVariable Long id) {
        return svcCasa.eliminarCasa(id);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<DtoCasaOut> obtenerCasaPorId(@PathVariable Long id) {
        return svcCasa.obtenerCasaPorId(id);
    }

    @GetMapping("/zona/{idZona}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<List<DtoCasaOut>> obtenerCasasPorZona(@PathVariable Long idZona) {
        return svcCasa.buscarPorZona(idZona);
    }
}