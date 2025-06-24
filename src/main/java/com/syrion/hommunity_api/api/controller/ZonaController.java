package com.syrion.hommunity_api.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syrion.hommunity_api.api.dto.DtoZonaIn;
import com.syrion.hommunity_api.api.entity.Zona;
import com.syrion.hommunity_api.api.service.SvcZona;

@RestController
@RequestMapping("/zona")
public class ZonaController {

    private final SvcZona svcZona;

    public ZonaController(SvcZona svcZona) {
        this.svcZona = svcZona;
    }

    @PostMapping
    public ResponseEntity<Zona> crearZona(@RequestBody DtoZonaIn dto) {
        Zona zonaCreada = svcZona.crearZona(dto);
        return ResponseEntity.ok(zonaCreada);
    }
}