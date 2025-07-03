package com.syrion.hommunity_api.api.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DtoZonaOut {

    @JsonProperty("idZona")
    private Long idZona;
    
    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("codigo_postal")
    private Integer codigoPostal;

    @JsonProperty("municipio")
    private String municipio;

    @JsonProperty("colonia")
    private String colonia;
}


    

