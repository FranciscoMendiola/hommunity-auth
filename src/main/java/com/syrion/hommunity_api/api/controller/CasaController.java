package com.syrion.hommunity_api.api.controller;

import java.util.List;

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

import com.syrion.hommunity_api.api.dto.DtoCasaIn;
import com.syrion.hommunity_api.api.entity.Casa;
import com.syrion.hommunity_api.api.service.SvcCasa;

@RestController
@RequestMapping("/casa")
public class CasaController {

    private final SvcCasa svcCasa;

    public CasaController(SvcCasa svcCasa) {
        this.svcCasa = svcCasa;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<?> crearCasa(@RequestBody DtoCasaIn casaIn) {
        svcCasa.crearCasa(casaIn);
        return new ResponseEntity<>("Casa creada exitosamente", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<?> eliminarCasa(@PathVariable Long id) {
        svcCasa.eliminarCasa(id);
        return new ResponseEntity<>("Casa eliminada exitosamente", HttpStatus.OK);
    }

    @GetMapping("/zona/{idZona}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Residente')")
    public ResponseEntity<List<Casa>> obtenerPorZona(@PathVariable Long idZona) {
        List<Casa> casas = svcCasa.buscarPorZona(idZona);
        return new ResponseEntity<>(casas, HttpStatus.OK);
    }

}
