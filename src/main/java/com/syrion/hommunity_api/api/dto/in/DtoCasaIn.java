package com.syrion.hommunity_api.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCasaIn {

    @JsonProperty("numero")
    @NotNull(message = "El número de la casa es obligatorio")
    private String numero;

    @JsonProperty("calle")
    @NotNull(message = "La calle es obligatoria")

    private String calle;

    @JsonProperty("idZona")   
    private Long idZona;
}
