package com.syrion.hommunity_api.common.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.out.DtoZonaOut;
import com.syrion.hommunity_api.api.entity.Zona;

@Service
public class MapperZona {
    
    
    public DtoZonaOut fromZona(Zona zona) {
         DtoZonaOut dto = new DtoZonaOut();
         dto.setIdZona(zona.getIdZona());
         dto.setNombre(zona.getNombre());
         dto.setCodigoPostal(zona.getCodigoPostal());
         dto.setMunicipio(zona.getMunicipio());
         dto.setColonia(zona.getColonia());
         return dto;
     }

     public List<DtoZonaOut> fromZonaList(List<Zona> zonas) {
        return zonas.stream()
                .map(this::fromZona)
                .collect(Collectors.toList());
    }
}

