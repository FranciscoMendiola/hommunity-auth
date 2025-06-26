package com.syrion.hommunity_api.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.DtoCasaIn;
import com.syrion.hommunity_api.api.entity.Casa;
import com.syrion.hommunity_api.api.entity.Zona;
import com.syrion.hommunity_api.api.repository.CasaRepository;
import com.syrion.hommunity_api.api.repository.ZonaRepository;

@Service
public class SvcCasaImp implements SvcCasa {

    private final CasaRepository casaRepository;
    private final ZonaRepository zonaRepository;

    public SvcCasaImp(CasaRepository casaRepository, ZonaRepository zonaRepository) {
        this.casaRepository = casaRepository;
        this.zonaRepository = zonaRepository;
    }

    @Override
    public void crearCasa(DtoCasaIn casaIn) {
        Optional<Zona> zona = zonaRepository.findById(casaIn.getIdZona());
        if (zona.isEmpty()) {
            throw new RuntimeException("Zona no encontrada con id: " + casaIn.getIdZona());
        }

        Casa casa = new Casa();
        casa.setNumero(casaIn.getNumero());
        casa.setCalle(casaIn.getCalle());
        casa.setIdZona(zona.get());

        casaRepository.save(casa);
    }

    @Override
    public void eliminarCasa(Long idCasa) {
        if (!casaRepository.existsById(idCasa)) {
            throw new RuntimeException("Casa no encontrada con id: " + idCasa);
        }

        casaRepository.deleteById(idCasa);
    }

    @Override
    public List<Casa> buscarPorZona(Long idZona) {
    return casaRepository.findByIdZonaIdZona(idZona);
    }

}
