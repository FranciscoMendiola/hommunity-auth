package com.syrion.hommunity_api.api.controller;

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

import com.syrion.hommunity_api.api.dto.DtoFamiliaIn;
import com.syrion.hommunity_api.api.service.SvcFamilia;

@RestController
@RequestMapping("/familia")
public class FamiliaController {

    private final SvcFamilia svcFamilia;

    public FamiliaController(SvcFamilia svcFamilia) {
        this.svcFamilia = svcFamilia;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<?> crearFamilia(@RequestBody DtoFamiliaIn familiaIn) {
        svcFamilia.crearFamilia(familiaIn);
        return new ResponseEntity<>("Familia creada exitosamente", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<?> eliminarFamilia(@PathVariable Long id) {
        svcFamilia.eliminarFamilia(id);
        return new ResponseEntity<>("Familia eliminada exitosamente", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<?> obtenerFamiliaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(svcFamilia.obtenerFamiliaPorId(id), HttpStatus.OK);
    }

}
