package com.syrion.hommunity_api.api.service;

import org.springframework.http.ResponseEntity;

import com.syrion.hommunity_api.api.dto.in.DtoAuthIn;
import com.syrion.hommunity_api.api.dto.out.DtoAuthOut;

public interface SvcAuth {

    ResponseEntity<DtoAuthOut> login(DtoAuthIn in);
    
}
