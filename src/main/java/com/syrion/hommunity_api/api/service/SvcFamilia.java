package com.syrion.hommunity_api.api.service;

import java.util.List;

import com.syrion.hommunity_api.api.dto.DtoFamiliaIn;
import com.syrion.hommunity_api.api.entity.Familia;

public interface SvcFamilia {
    void crearFamilia(DtoFamiliaIn familiaIn);
    void eliminarFamilia(Long idFamilia);
    Object obtenerFamiliaPorId(Long id);
    List<Familia> obtenerFamiliasPorZona(Long idZona);
}

