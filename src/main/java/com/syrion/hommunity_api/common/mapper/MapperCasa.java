package com.syrion.hommunity_api.common.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoCasaIn;
import com.syrion.hommunity_api.api.dto.out.DtoCasaOut;
import com.syrion.hommunity_api.api.entity.Casa;
import com.syrion.hommunity_api.api.entity.Zona;

@Service
public class MapperCasa {
    
    public Casa fromCasa(DtoCasaIn in, Zona zona) {
        Casa casa = new Casa();
        casa.setNumero(in.getNumero());
        casa.setCalle(in.getCalle());
        casa.setIdZona(zona);
        return casa;
    }

    public DtoCasaOut fromCasa(Casa casa) {
        DtoCasaOut out = new DtoCasaOut();
        out.setIdCasa(casa.getIdCasa());
        out.setNumero(casa.getNumero());
        out.setCalle(casa.getCalle());
        out.setIdZona(casa.getIdZona().getIdZona());
        return out;
    }

    public List<DtoCasaOut> fromCasaList(List<Casa> casas) {
        return casas.stream()
                .map(this::fromCasa)
                .collect(Collectors.toList());
    }
}
