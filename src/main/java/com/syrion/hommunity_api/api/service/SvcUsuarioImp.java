package com.syrion.hommunity_api.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoUsuarioIn;
import com.syrion.hommunity_api.api.entity.Usuario;
import com.syrion.hommunity_api.api.repository.UsuarioRepository;
import com.syrion.hommunity_api.common.dto.ApiResponse;
import com.syrion.hommunity_api.common.mapper.MapperUsuario;
import com.syrion.hommunity_api.exception.ApiException;
import com.syrion.hommunity_api.exception.DBAccessException;

@Service
public class SvcUsuarioImp implements SvcUsuario {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MapperUsuario mapper;

    @Override
    public ResponseEntity<Usuario> getUsuario(Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);

            if (usuario == null)
                throw new ApiException(HttpStatus.NOT_FOUND, "El id del usuario no existe");

            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> createUsusario(DtoUsuarioIn in) {
        try {
            in.setContraseña(passwordEncoder.encode(in.getContraseña()));

            Usuario usuario = mapper.fromUsuario(in);
            
            usuarioRepository.save(usuario);

            return new ResponseEntity<>(new ApiResponse("Usuario creado correctamente"), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
}
