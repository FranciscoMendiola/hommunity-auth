package com.syrion.hommunity_api.common.mapper;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoCodigoIn;
import com.syrion.hommunity_api.api.dto.in.DtoCodigoResidenteIn;
import com.syrion.hommunity_api.api.entity.QR;
import com.syrion.hommunity_api.api.entity.Usuario;

@Service
public class MapperQR {

    public QR fromQR(DtoCodigoIn in) {
        QR qr = new QR();
        qr.setCodigo(generateNumericCode());
        qr.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        qr.setVigente(true);
        return qr;
    }

    public QR fromQR(DtoCodigoResidenteIn in, Usuario usuario) {
        QR qr = new QR();
        qr.setCodigo(generateNumericCode());
        qr.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        qr.setVigente(true);
        qr.setIdUsuario(usuario);
        return qr;
    }

    private String generateNumericCode() {
        long number = 1000000000L + (long)(Math.random() * 9000000000L);
        return String.valueOf(number);
    }
}
