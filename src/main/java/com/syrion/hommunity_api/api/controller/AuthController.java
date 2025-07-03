package com.syrion.hommunity_api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syrion.hommunity_api.api.dto.in.DtoAuthIn;
import com.syrion.hommunity_api.api.dto.out.DtoAuthOut;
import com.syrion.hommunity_api.api.service.SvcAuth;
import com.syrion.hommunity_api.exception.ApiException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SvcAuth svc;

    @PostMapping  
    public ResponseEntity<DtoAuthOut> login(@Valid @RequestBody DtoAuthIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

        return svc.login(in);
    }
}
