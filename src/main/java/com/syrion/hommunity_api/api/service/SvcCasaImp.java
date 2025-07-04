package com.syrion.hommunity_api.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoCasaIn;
import com.syrion.hommunity_api.api.dto.out.DtoCasaOut;
import com.syrion.hommunity_api.api.entity.Casa;
import com.syrion.hommunity_api.api.entity.Zona;
import com.syrion.hommunity_api.api.repository.CasaRepository;
import com.syrion.hommunity_api.api.repository.ZonaRepository;
import com.syrion.hommunity_api.common.dto.ApiResponse;
import com.syrion.hommunity_api.common.mapper.MapperCasa;
import com.syrion.hommunity_api.exception.ApiException;
import com.syrion.hommunity_api.exception.DBAccessException;

@Service
public class SvcCasaImp implements SvcCasa {

    @Autowired
    private CasaRepository casaRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private MapperCasa mapperCasa;

    @Override
    public ResponseEntity<DtoCasaOut> crearCasa(DtoCasaIn casaIn) {
        try {
            Zona zona = zonaRepository.findById(casaIn.getIdZona())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Zona con id " + casaIn.getIdZona() + " no encontrada."));

            Casa casa = mapperCasa.fromCasa(casaIn, zona);

            casaRepository.save(casa);

            DtoCasaOut casaOut = mapperCasa.fromCasa(casa);

            return new ResponseEntity<>(casaOut, HttpStatus.CREATED);
            
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> eliminarCasa(Long idCasa) {
        if (!casaRepository.existsById(idCasa)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Casa no encontrada con id: " + idCasa));
        }

        casaRepository.deleteById(idCasa);
        return ResponseEntity.ok(new ApiResponse("Casa eliminada exitosamente"));
    }

    @Override
    public ResponseEntity<List<DtoCasaOut>> buscarPorZona(Long idZona) {
        List<Casa> casas = casaRepository.findByIdZonaIdZona(idZona);
        List<DtoCasaOut> casasOut = mapperCasa.fromCasaList(casas);
        return ResponseEntity.ok(casasOut);
    }

    @Override
    public ResponseEntity<DtoCasaOut> obtenerCasaPorId(Long id) {
        Optional<Casa> casaOpt = casaRepository.findById(id);
        if (casaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        DtoCasaOut casaOut = mapperCasa.fromCasa(casaOpt.get());
        return ResponseEntity.ok(casaOut);
    }
}