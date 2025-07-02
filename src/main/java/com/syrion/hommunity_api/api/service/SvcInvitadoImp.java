package com.syrion.hommunity_api.api.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoInvitadoIn;
import com.syrion.hommunity_api.api.dto.out.DtoInvitadoOut;
import com.syrion.hommunity_api.api.entity.Invitado;
import com.syrion.hommunity_api.api.entity.Usuario;
import com.syrion.hommunity_api.api.repository.InvitadoRepository;
import com.syrion.hommunity_api.api.repository.UsuarioRepository;
import com.syrion.hommunity_api.common.dto.ApiResponse;
import com.syrion.hommunity_api.common.mapper.MapperInvitado;
import com.syrion.hommunity_api.exception.ApiException;
import com.syrion.hommunity_api.exception.DBAccessException;

@Service
public class SvcInvitadoImp implements SvcInvitado {

    @Autowired
    private InvitadoRepository repoInvitado;

    @Autowired
    private MapperInvitado mapperInvitado;

    @Autowired
    private UsuarioRepository repoUsuario;

    @Override
    public ResponseEntity<List<DtoInvitadoOut>> getInvitados() {
        try {
            List<Invitado> invitados = repoInvitado.findAll();

            List<DtoInvitadoOut> dtoInvitados = mapperInvitado.fromInvitados(invitados);

            return new ResponseEntity<>(dtoInvitados, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<DtoInvitadoOut> getInvitado(Long id) {
        try {
            Invitado invitado = validateId(id);

            DtoInvitadoOut dtoInvitado = mapperInvitado.fromInvitado(invitado);

            return new ResponseEntity<>(dtoInvitado, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> createInvitado(DtoInvitadoIn in) {
        try {
            if (in.getFechaVisita().before(Date.valueOf(LocalDate.now())))
                throw new ApiException(HttpStatus.BAD_REQUEST, "La fecha de visita no puede ser anterior a la fecha actual.");

            Usuario usuario = repoUsuario.findById(in.getIdUsuario()).orElse(null);

            if (usuario == null)
                throw new ApiException(HttpStatus.NOT_FOUND, "El id del usuario residente no esta registrado.");

            Invitado invitado = mapperInvitado.fromInvitado(in);
            invitado.setIdUsuario(usuario);

            repoInvitado.save(invitado);

            return new ResponseEntity<>(new ApiResponse("Invitado creado correctamente"), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> setHoraEntrada(Long id) {
        try {
            Invitado invitado = validateId(id);

            
            if (!invitado.getHoraEntrada().toLocalTime().equals(LocalTime.MIDNIGHT)) {
                throw new ApiException(HttpStatus.CONFLICT, "La hora de entrada ya ha sido registrada.");
            }

            invitado.setHoraEntrada(new Time(System.currentTimeMillis()));
            repoInvitado.save(invitado);

            return new ResponseEntity<>(new ApiResponse("Hora de entrada registrada correctamente."),
                    HttpStatus.ACCEPTED);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> setHoraSalida(Long id) {
        try {
            Invitado invitado = validateId(id);

            if (!invitado.getHoraSalida().toLocalTime().equals(LocalTime.MIDNIGHT))
                throw new ApiException(HttpStatus.CONFLICT, "La hora de salida ya ha sido registrada.");

            if (invitado.getHoraEntrada().toLocalTime().equals(LocalTime.MIDNIGHT))
                throw new ApiException(HttpStatus.CONFLICT, "La hora de de entrada no ha sido registrada.");

                
            invitado.setHoraSalida(new Time(System.currentTimeMillis()));
                
            repoInvitado.save(invitado); 
            
            return new ResponseEntity<>(new ApiResponse("Hora de salida registrada correctamente."),
                    HttpStatus.ACCEPTED);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    private Invitado validateId(Long id) {

        Invitado invitado = repoInvitado.findById(id).orElse(null);

        if (invitado == null)
            throw new ApiException(HttpStatus.NOT_FOUND, "El id del invitado no esta registrado.");

        return invitado;
    }
}
