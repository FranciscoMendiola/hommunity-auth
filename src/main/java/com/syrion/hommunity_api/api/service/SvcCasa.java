package com.syrion.hommunity_api.api.service;

import java.util.List;

import com.syrion.hommunity_api.api.dto.DtoCasaIn;
import com.syrion.hommunity_api.api.entity.Casa;


public interface SvcCasa {
    void crearCasa(DtoCasaIn casaIn);
    void eliminarCasa(Long idCasa);
    List<Casa> buscarPorZona(Long idZona);
}
