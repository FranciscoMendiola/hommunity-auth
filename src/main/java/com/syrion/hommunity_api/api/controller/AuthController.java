package com.syrion.hommunity_api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syrion.hommunity_api.api.dto.in.DtoAuthIn;
import com.syrion.hommunity_api.api.dto.out.DtoAuthOut;
import com.syrion.hommunity_api.api.service.SvcAuth;
import com.syrion.hommunity_api.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Autenticación de usuarios")
public class AuthController {

    @Autowired
    private SvcAuth svc;

    // Login de usuario registrado y aprobado
    @PostMapping
    @Operation(summary = "Iniciar sesión (Login)", description = "Permite a un usuario registrado iniciar sesión en la aplicación.")
    public ResponseEntity<DtoAuthOut> login(@Valid @RequestBody DtoAuthIn in, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "Validation error";
            throw new ApiException(HttpStatus.BAD_REQUEST, errorMessage);
        }
        return svc.login(in);
    }
}
