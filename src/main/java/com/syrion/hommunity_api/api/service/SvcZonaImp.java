package com.syrion.hommunity_api.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoZonaIn;
import com.syrion.hommunity_api.api.dto.out.DtoZonaOut;
import com.syrion.hommunity_api.api.entity.Zona;
import com.syrion.hommunity_api.api.repository.ZonaRepository;
import com.syrion.hommunity_api.common.dto.ApiResponse;
import com.syrion.hommunity_api.common.mapper.MapperZona;
import com.syrion.hommunity_api.exception.DBAccessException;

@Service
public class SvcZonaImp implements SvcZona {

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private MapperZona mapperZona;

    @Override
    public ResponseEntity<ApiResponse> crearZona(DtoZonaIn dto) {
        try {
            Zona zona = new Zona();
            zona.setNombre(dto.getNombre());
            zona.setCodigoPostal(dto.getCodigoPostal());
            zona.setMunicipio(dto.getMunicipio());
            zona.setColonia(dto.getColonia());

            zonaRepository.save(zona);
            return new ResponseEntity<>(new ApiResponse("Zona creada correctamente"), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<List<DtoZonaOut>> obtenerZonas() {
        try {
            List<Zona> zonas = zonaRepository.findAll();
            List<DtoZonaOut> dtoZonas = mapperZona.fromZonaList(zonas);
            return new ResponseEntity<>(dtoZonas, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
}