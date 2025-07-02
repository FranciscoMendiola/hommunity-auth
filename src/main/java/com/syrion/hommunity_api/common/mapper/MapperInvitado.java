package com.syrion.hommunity_api.common.mapper;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.dto.in.DtoInvitadoIn;
import com.syrion.hommunity_api.api.entity.Invitado;

@Service
public class MapperInvitado {

    public Invitado fromInvitado(DtoInvitadoIn in) {
        Invitado invitado = new Invitado();
        invitado.setNombre(in.getNombre());
        invitado.setApellidoPaterno(in.getApellidoPaterno());
        invitado.setApellidoMaterno(in.getApellidoMaterno());
        invitado.setFechaVisita(in.getFechaVisita());

        invitado.setHoraEntrada(Time.valueOf(LocalTime.MIDNIGHT));
        invitado.setHoraSalida(Time.valueOf(LocalTime.MIDNIGHT));


        return invitado;
    }
    
}
