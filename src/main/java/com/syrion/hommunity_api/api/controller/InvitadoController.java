package com.syrion.hommunity_api.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syrion.hommunity_api.api.dto.in.DtoInvitadoIn;
import com.syrion.hommunity_api.api.entity.Invitado;
import com.syrion.hommunity_api.api.service.SvcInvitado;
import com.syrion.hommunity_api.common.dto.ApiResponse;
import com.syrion.hommunity_api.exception.ApiException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/invitado")
public class InvitadoController {

    @Autowired
    SvcInvitado svc;
    
    @GetMapping
    public ResponseEntity<List<Invitado>> getInvitados() {
        return svc.getInvitados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invitado> getInvitado(@Valid @PathVariable Long id) {
        return svc.getInvitado(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createInvitado(@Valid @RequestBody DtoInvitadoIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

        return svc.createInvitado(in);
    }

    @PatchMapping("{id}/entrada")
    public ResponseEntity<ApiResponse> seHoraEntrada(@Valid @PathVariable Long id) {
        return svc.setHoraEntrada(id);
    }

    @PatchMapping("{id}/salida")
    public ResponseEntity<ApiResponse> seHoraSalida(@Valid @PathVariable Long id) {
        return svc.setHoraSalida(id);
    }
}
