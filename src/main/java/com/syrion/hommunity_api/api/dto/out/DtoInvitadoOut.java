package com.syrion.hommunity_api.api.dto.out;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoInvitadoOut {

    @JsonProperty("idInvitado")
    private Long idInvitado;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellidoPaterno")
    private String apellidoPaterno;

    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;

    @JsonProperty("fechaVisita")
    private Date fechaVisita;

    @JsonProperty("horaEntrada")
    private Time horaEntrada;
    
    @JsonProperty("horaSalida")
    private Time horaSalida;

    @JsonProperty("idUsuario")
    private Long idUsuario;
}
