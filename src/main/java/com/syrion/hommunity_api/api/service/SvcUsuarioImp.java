package com.syrion.hommunity_api.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoEstadoUsuariIn;
import com.syrion.hommunity_api.api.dto.in.DtoUsuarioContraseñaIn;
import com.syrion.hommunity_api.api.dto.in.DtoUsuarioIn;
import com.syrion.hommunity_api.api.dto.out.DtoUsuarioOut;
import com.syrion.hommunity_api.api.entity.Familia;
import com.syrion.hommunity_api.api.entity.Usuario;
import com.syrion.hommunity_api.api.entity.Zona;
import com.syrion.hommunity_api.api.enums.EstadoUsuario;
import com.syrion.hommunity_api.api.repository.FamiliaRepository;
import com.syrion.hommunity_api.api.repository.UsuarioRepository;
import com.syrion.hommunity_api.api.repository.ZonaRepository;
import com.syrion.hommunity_api.common.dto.ApiResponse;
import com.syrion.hommunity_api.common.mapper.MapperUsuario;
import com.syrion.hommunity_api.exception.ApiException;
import com.syrion.hommunity_api.exception.DBAccessException;

@Service
public class SvcUsuarioImp implements SvcUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private FamiliaRepository familiaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MapperUsuario mapper;

    @Override
    public ResponseEntity<DtoUsuarioOut> getUsuario(Long id) {
        try {
            Usuario usuario = validateId(id);

            DtoUsuarioOut usuarioOut = mapper.fromDtoUsuario(usuario);
            return new ResponseEntity<>(usuarioOut, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> createUsuario(DtoUsuarioIn in) {
        try {
            Zona zona = zonaRepository.findById(in.getIdZona())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Zona no encontrada con id: " + in.getIdZona()));
    
                
            Familia familia = familiaRepository.findById(in.getIdFamilia())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Familia no encontrada con id: " + in.getIdFamilia()));
                
            Usuario usuario = mapper.fromUsuario(in);
            usuario.setIdZona(zona);
            usuario.setIdFamilia(familia);

            in.setContraseña(passwordEncoder.encode(in.getContraseña()));
            
            usuarioRepository.save(usuario);

            return new ResponseEntity<>(new ApiResponse("Usuario creado correctamente"), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteUsuario(Long id) {
        try {
            Usuario usuario = validateId(id);

            usuarioRepository.delete(usuario);

            return new ResponseEntity<>(new ApiResponse("Usuario eliminado correctamente"), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<List<DtoUsuarioOut>> getUsuariosPorZona(Long idZona) {
        try {
            zonaRepository.findById(idZona)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Zona no encontrada con id: " + idZona));

            List<Usuario> usuarios = usuarioRepository.findByIdZona(idZona);

            List<DtoUsuarioOut> usuariosOut = mapper.fromListDto(usuarios);

            return new ResponseEntity<>(usuariosOut, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<List<DtoUsuarioOut>> getUsuariosPorFamilia(Long idFamilia) {
        try {
            familiaRepository.findById(idFamilia)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Familia no encontrada con id: " + idFamilia));

            List<Usuario> usuarios = usuarioRepository.findByIdFamilia(idFamilia);

            List<DtoUsuarioOut> usuariosOut = mapper.fromListDto(usuarios);

            return new ResponseEntity<>(usuariosOut, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> updateEstadoUsuario(Long id, DtoEstadoUsuariIn in) {
        try {
            Usuario usuario = validateId(id);

            if (in.getEstado().toLowerCase() != EstadoUsuario.APROBADO.getValor() || in.getEstado() != EstadoUsuario.PENDIENTE.getValor())
                throw new ApiException(HttpStatus.BAD_REQUEST, "El estado del usuario no es valido");

            if (!in.getEstado().toLowerCase().equals(usuario.getEstado().getValor()))
                usuario.setEstado(EstadoUsuario.valueOf(in.getEstado().toLowerCase()));

            usuarioRepository.save(usuario);

            return new ResponseEntity<>(new ApiResponse("Usuario actualizado correctamente"), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> updateContraseña(Long id, DtoUsuarioContraseñaIn in) {
        try {
            Usuario usuario = validateId(id);

            if (!passwordEncoder.matches(in.getContraseñaActual(), usuario.getContraseña()))
                throw new ApiException(HttpStatus.BAD_REQUEST, "La contraseña actual es incorrecta");

            if (in.getNuevaContraseña().equals(in.getContraseñaActual()))
                throw new ApiException(HttpStatus.BAD_REQUEST, "La nueva contraseña no puede ser igual a la actual");

            usuario.setContraseña(passwordEncoder.encode(in.getNuevaContraseña()));

            usuarioRepository.save(usuario);
            return new ResponseEntity<>(new ApiResponse("Contraseña actualizada correctamente"), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    private Usuario  validateId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null)
            throw new ApiException(HttpStatus.NOT_FOUND, "El id del usuario no esta registrado.");

        return usuario;
    }
}
