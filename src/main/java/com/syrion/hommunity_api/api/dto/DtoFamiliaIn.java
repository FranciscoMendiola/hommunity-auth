package com.syrion.hommunity_api.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DtoFamiliaIn {

    @JsonProperty("apellido")
    @NotNull(message = "El apellido es obligatorio")
    private String apellido;

    @JsonProperty("estado")
    @NotNull(message = "El estado es obligatorio")
    private String estado;

    @JsonProperty("fotoIdentificacion")
    @NotNull(message = "La foto de identificación es obligatoria")
    private String fotoIdentificacion;

    @JsonProperty("idCasa")
    @NotNull(message = "La casa es obligatoria")
    private Long idCasa;

    @JsonProperty("idUsuarioRegistrador")
    @NotNull(message = "El usuario registrador es obligatorio")
    private Long idUsuarioRegistrador;   
}
