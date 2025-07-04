package com.syrion.hommunity_api.api.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoCodigoIn;
import com.syrion.hommunity_api.api.dto.in.DtoCodigoResidenteIn;
import com.syrion.hommunity_api.api.entity.Invitado;
import com.syrion.hommunity_api.api.entity.QR;
import com.syrion.hommunity_api.api.entity.Usuario;
import com.syrion.hommunity_api.api.repository.InvitadoRepository;
import com.syrion.hommunity_api.api.repository.QrRepository;
import com.syrion.hommunity_api.api.repository.UsuarioRepository;
import com.syrion.hommunity_api.common.dto.ApiResponse;
import com.syrion.hommunity_api.common.mapper.MapperQR;
import com.syrion.hommunity_api.exception.ApiException;
import com.syrion.hommunity_api.exception.DBAccessException;

@Service
public class SvcQrImp implements SvcQr {

    @Autowired
    private QrRepository repoQr;

    @Autowired
    private MapperQR mapper;

    @Autowired
    private InvitadoRepository repoInvitado;

    @Autowired
    private UsuarioRepository repoUsuario;



    @Override
    public ResponseEntity<List<QR>> getCodigos() {
        try {
            List<QR> qrs = repoQr.findAll();

            return new ResponseEntity<>(qrs, HttpStatus.OK );
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<List<QR>> getCodigosActivos() {
        try {
            List<QR> qrs = repoQr.findByActiveStatus();
            
            return new ResponseEntity<>(qrs, HttpStatus.OK );
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<QR> getCodigo(Long id) {
        try {
            QR qr = validateId(id);

            return new ResponseEntity<>(qr, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> createCodigo(DtoCodigoIn in) {
        try {
            Invitado invitado = repoInvitado.findById(in.getIdInvitado()).orElse(null);

            if (invitado == null)
                throw new ApiException(HttpStatus.BAD_REQUEST, "El invitado no esta registrado.");

            if (Date.valueOf(LocalDate.now()).after(invitado.getFechaVisita()))
                throw new ApiException(HttpStatus.BAD_REQUEST, "El QR no se puede asociar a un invitado con fecha de visita anterior a la fecha actual.");

            QR qr = mapper.fromQR(in);

            qr.setIdInvitado(invitado);

            repoQr.save(qr);

            return new ResponseEntity<>(new ApiResponse("Codigo QR registrado correctamente"), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> validar(Long id) {
        try {
            QR qr = validateId(id);

            if (qr.getIdInvitado() != null) {
                if (qr.getIdInvitado().getFechaVisita().before(Date.valueOf(LocalDate.now()))) {
                    qr.setVigente(false);
                    repoQr.save(qr);
                    throw new ApiException(HttpStatus.BAD_REQUEST, "El código QR ha expirado.");
                }
            }
            // Si es de residente, no requiere esta validación de fecha


            if (!qr.getVigente())
                throw new ApiException(HttpStatus.BAD_REQUEST, "El codigo QR ya ha sido utilizado.");


            qr.setVigente(false);
            repoQr.save(qr);

            return new ResponseEntity<>(new ApiResponse("Codigo QR validado correctamente"), HttpStatus.OK);
        } catch (ApiException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    private QR validateId(Long id) {

        QR qr = repoQr.findById(id).orElse(null);

        if (qr == null)
            throw new ApiException(HttpStatus.NOT_FOUND, "El id del QR no esta registrado.");

        return qr;
    }

    @Override
    public ResponseEntity<ApiResponse> createCodigoResidente(DtoCodigoResidenteIn in) {
        try {
            Usuario usuario = repoUsuario.findById(in.getIdUsuario())
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El usuario residente no está registrado."));

            QR qr = mapper.fromQR(in, usuario);

            repoQr.save(qr);

            return new ResponseEntity<>(new ApiResponse("Código QR para residente creado correctamente"), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

}
