package com.syrion.hommunity_api.api.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.DtoFamiliaIn;
import com.syrion.hommunity_api.api.entity.Casa;
import com.syrion.hommunity_api.api.entity.Familia;
import com.syrion.hommunity_api.api.entity.Usuario;
import com.syrion.hommunity_api.api.enums.EstadoUsuario;
import com.syrion.hommunity_api.api.repository.CasaRepository;
import com.syrion.hommunity_api.api.repository.FamiliaRepository;
import com.syrion.hommunity_api.api.repository.UsuarioRepository;

@Service
public class SvcFamiliaImp implements SvcFamilia {

    private final FamiliaRepository familiaRepository;
    private final CasaRepository casaRepository;
    private final UsuarioRepository usuarioRepository;

    public SvcFamiliaImp(FamiliaRepository familiaRepository,
                         CasaRepository casaRepository,
                         UsuarioRepository usuarioRepository) {
        this.familiaRepository = familiaRepository;
        this.casaRepository = casaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void crearFamilia(DtoFamiliaIn familiaIn) {
        Optional<Casa> casa = casaRepository.findById(familiaIn.getIdCasa());
        if (casa.isEmpty()) {
            throw new RuntimeException("Casa no encontrada con id: " + familiaIn.getIdCasa());
        }

        Optional<Usuario> usuario = usuarioRepository.findById(familiaIn.getIdUsuarioRegistrador());
        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuario registrador no encontrado con id: " + familiaIn.getIdUsuarioRegistrador());
        }

        Familia familia = new Familia();
        familia.setApellido(familiaIn.getApellido());

        try {
            EstadoUsuario estado = EstadoUsuario.fromValor(familiaIn.getEstado());
            familia.setEstado(estado);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("EstadoUsuario inválido: " + familiaIn.getEstado());
        }

        familia.setFotoIdentificacion(familiaIn.getFotoIdentificacion());
        familia.setFechaRegistro(Timestamp.from(Instant.now()));

        familia.setIdCasa(casa.get());
        familia.setIdUsuarioRegistrador(usuario.get());

        familiaRepository.save(familia);
    }

    @Override
    public void eliminarFamilia(Long idFamilia) {
        if (!familiaRepository.existsById(idFamilia)) {
            throw new RuntimeException("Familia no encontrada con id: " + idFamilia);
        }
        familiaRepository.deleteById(idFamilia);
    }

    @Override
    public Object obtenerFamiliaPorId(Long id) {
        return familiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Familia no encontrada con id: "
                        + id));
    
    }            
}
