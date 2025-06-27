package com.syrion.hommunity_api.common.mapper;

import java.sql.Time;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoCodigoIn;
import com.syrion.hommunity_api.api.entity.QR;

@Service
public class MapperQR {

    public QR fromQR(DtoCodigoIn in) {
        QR qr = new QR();
        qr.setCodigo(in.getCodigo());
        qr.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        qr.setVigente(true);

        return qr;
    }
}
