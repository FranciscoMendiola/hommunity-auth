package com.syrion.hommunity_api.api.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoFamiliaIn;
import com.syrion.hommunity_api.api.dto.out.DtoFamiliaOut;
import com.syrion.hommunity_api.api.entity.Casa;
import com.syrion.hommunity_api.api.entity.Familia;
import com.syrion.hommunity_api.api.entity.Usuario;
import com.syrion.hommunity_api.api.enums.EstadoUsuario;
import com.syrion.hommunity_api.api.repository.CasaRepository;
import com.syrion.hommunity_api.api.repository.FamiliaRepository;
import com.syrion.hommunity_api.api.repository.UsuarioRepository;
import com.syrion.hommunity_api.api.repository.ZonaRepository;
import com.syrion.hommunity_api.common.dto.ApiResponse;
import com.syrion.hommunity_api.common.mapper.MapperFamilia;
import com.syrion.hommunity_api.exception.ApiException;
import com.syrion.hommunity_api.exception.DBAccessException;

@Service
public class SvcFamiliaImp implements SvcFamilia {

    private final FamiliaRepository familiaRepository;
    private final CasaRepository casaRepository;
    private final UsuarioRepository usuarioRepository;
    private MapperFamilia mapperFamilia;

    @Autowired
    private ZonaRepository zonaRepository;

    public SvcFamiliaImp(FamiliaRepository familiaRepository,
                         CasaRepository casaRepository,
                         UsuarioRepository usuarioRepository,
                         MapperFamilia mapperFamilia) {
        this.familiaRepository = familiaRepository;
        this.casaRepository = casaRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapperFamilia = mapperFamilia;
    }

    @Override
    public ResponseEntity<ApiResponse> crearFamilia(DtoFamiliaIn familiaIn) {
        Casa casa = casaRepository.findById(familiaIn.getIdCasa())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Casa no encontrada con id: " + familiaIn.getIdCasa()));

        Usuario usuario = usuarioRepository.findById(familiaIn.getIdUsuarioRegistrador())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Usuario registrador no encontrado con id: " + familiaIn.getIdUsuarioRegistrador()));

        Familia familia = new Familia();
        familia.setApellido(familiaIn.getApellido());

        try {
            EstadoUsuario estado = EstadoUsuario.fromValor(familiaIn.getEstado());
            familia.setEstado(estado);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "EstadoUsuario inválido: " + familiaIn.getEstado());
        }

        familia.setFotoIdentificacion(familiaIn.getFotoIdentificacion());
        familia.setFechaRegistro(Timestamp.from(Instant.now()));
        familia.setIdCasa(casa);
        familia.setIdUsuarioRegistrador(usuario);

        familiaRepository.save(familia);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Familia creada exitosamente"));
    }

    @Override
    public ResponseEntity<ApiResponse> eliminarFamilia(Long idFamilia) {
        if (!familiaRepository.existsById(idFamilia)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Familia no encontrada con id: " + idFamilia));
        }
        familiaRepository.deleteById(idFamilia);
        return ResponseEntity.ok(new ApiResponse("Familia eliminada exitosamente"));
    }

    public ResponseEntity<DtoFamiliaOut> obtenerFamiliaPorId(Long id) {
        Familia familia = familiaRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Familia no encontrada con id: " + id));
        DtoFamiliaOut familiaOut = mapperFamilia.fromFamilia(familia);
        return ResponseEntity.ok(familiaOut);
    }
    
    @Override
    public ResponseEntity<List<DtoFamiliaOut>> obtenerFamiliasPorZona(Long idZona) {
        try {

            zonaRepository.findById(idZona)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Zona no encontrada con id: " + idZona));

            List<Familia> familias = familiaRepository.findByIdCasaIdZonaIdZona(idZona);

            List<DtoFamiliaOut> familiasOut = mapperFamilia.fromListDto(familias);

            
            return new ResponseEntity<>(familiasOut, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
}
