package com.syrion.hommunity_api.api.dto.in;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syrion.hommunity_api.api.entity.Usuario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoInvitadoIn {

    @JsonProperty("nombre")
    @NotNull(message = "El nombre es obligatorio") 
    private String nombre;

    @JsonProperty("apellidoPaterno")
    @NotNull(message = "El apellido paterno es obligatorio")
    private String apellidoPaterno;

    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;

    @JsonProperty("fechaVisita")
    @NotNull(message = "La fecha de visita es obligatoria")
    private Date fechaVisita;

    @JsonProperty("idUsuario")
    @NotNull(message = "El usuario residente es obligatorio")
    private Long idUsuario;
}
