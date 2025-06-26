package com.syrion.hommunity_api.api.service;

import com.syrion.hommunity_api.api.dto.DtoFamiliaIn;

public interface SvcFamilia {
    void crearFamilia(DtoFamiliaIn familiaIn);
    void eliminarFamilia(Long idFamilia);
}

