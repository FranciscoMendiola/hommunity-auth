package com.syrion.hommunity_api.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoZonaIn {
    private String nombre;
    private Integer codigoPostal;
    private String municipio;
    private String colonia;
}
