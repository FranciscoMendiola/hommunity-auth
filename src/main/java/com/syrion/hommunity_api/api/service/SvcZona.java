package com.syrion.hommunity_api.api.service;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.DtoZonaIn;
import com.syrion.hommunity_api.api.entity.Zona;
import com.syrion.hommunity_api.api.repository.ZonaRepository;

@Service
public class SvcZona {

    private final ZonaRepository zonaRepository;

    public SvcZona(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    public Zona crearZona(DtoZonaIn dto) {
        Zona zona = new Zona();
        zona.setNombre(dto.getNombre());
        zona.setCodigoPostal(dto.getCodigoPostal());
        zona.setMunicipio(dto.getMunicipio());
        zona.setColonia(dto.getColonia());
        return zonaRepository.save(zona);
    }
}
