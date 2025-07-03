package com.syrion.hommunity_api.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.out.DtoFamiliaOut;
import com.syrion.hommunity_api.api.entity.Familia;

@Service
public class MapperFamilia {

    public DtoFamiliaOut fromFamilia(Familia familia) {
        DtoFamiliaOut out = new DtoFamiliaOut();
        out.setIdFamilia(familia.getIdFamilia());
        out.setApellido(familia.getApellido());
        out.setEstado(familia.getEstado() != null ? familia.getEstado().name() : null);
        out.setFotoIdentificacion(familia.getFotoIdentificacion());
        out.setFechaRegistro(familia.getFechaRegistro());
        out.setIdCasa(familia.getIdCasa() != null ? familia.getIdCasa().getIdCasa() : null);
        out.setIdUsuarioRegistrador(
            familia.getIdUsuarioRegistrador() != null
                ? familia.getIdUsuarioRegistrador().getIdUsuario()
                : null
        );
        return out;
    }

    public List<DtoFamiliaOut> fromListDto(List<Familia> familias) {
        List<DtoFamiliaOut> out = new ArrayList<>();

        for (Familia familia : familias) {
            out.add(fromFamilia(familia));
        }

        return out;
    }
}

