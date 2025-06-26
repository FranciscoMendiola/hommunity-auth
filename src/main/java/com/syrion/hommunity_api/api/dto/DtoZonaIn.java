package com.syrion.hommunity_api.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoZonaIn {

    @JsonProperty("nombre")
    @NotNull(message = "El nombre de la zona es obligatorio")
    private String nombre;

    @JsonProperty("codigoPostal")
    @NotNull(message = "El código postal es obligatorio")
    private Integer codigoPostal;

    @JsonProperty("municipio")
    @NotNull(message = "El municipio es obligatorio")
    private String municipio;

    @JsonProperty("colonia")
    @NotNull(message = "La colonia es obligatoria")
    private String colonia;
}
