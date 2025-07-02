package com.syrion.hommunity_api.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syrion.hommunity_api.api.dto.in.DtoCodigoIn;
import com.syrion.hommunity_api.api.entity.QR;
import com.syrion.hommunity_api.api.service.SvcQr;
import com.syrion.hommunity_api.common.dto.ApiResponse;
import com.syrion.hommunity_api.exception.ApiException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/qr")
public class QrController {

    @Autowired
    private SvcQr svc;

    @GetMapping
    public ResponseEntity<List<QR>> getCodigos() {
        return svc.getCodigos();
    }

    @GetMapping("/active")
    public ResponseEntity<List<QR>> getCodigosActivos() {
        return svc.getCodigosActivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QR> getCodigo(@Valid @PathVariable Long id) {
        return svc.getCodigo(id);   
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCodigo(@Valid @RequestBody DtoCodigoIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());
        return svc.createCodigo(in);
    }

    @PostMapping("/{id}/validate")
    public ResponseEntity<ApiResponse> validar(@Valid @PathVariable Long id) {
        return svc.validar(id);
    }
}
